---
title: 设计模式
date: 2023-04-03 14:18:28
tags: 编程笔记
toc: true
password: target2023
comment: false
widgets:
  - type: toc
    position: right
    index: true
    collapsed: false
    depth: 3
---

案例学习

<!-- more -->

### 单例模式


单例模式是日常应用中最广泛的模式了，其目的就是令到单个进程中只存在一个类的实例，从而可以实现数据的共享，节省系统开销，防止io阻塞等等。

但是在多进程的应用中，单例模式就实现不了了，例如一些web应用，django，这些，因为会启动多条进程来监听http请求，这样的会通过单例模式是实现不了数据共享的，也就是实现不了单例模式的目的了，这时需要用进程间通信方法来实现数据共享。


**[Q]**
c++的``static a``; 这个``a``是多线程下的``static``值，还是多进程下的``static``值

**[A]**
C++中的``static``变量是多线程下的静态值，而不是多进程下的静态值。每个进程都有自己独立的内存空间，所以进程之间的静态变量是不共享的。如果需要在多进程之间共享变量，可以使用共享内存等机制。

---

**[Q]**
什么叫一个进程，我有一个仓库，里面有很多代码，代码有很多模块，其中有一个模块下面有个单例模式。这个仓库运行起来的时候只有一个可执行程序，那我在这个仓库代码下使用这个单例模式，算多线程还是多进程

**[A]**
在你的描述中，如果这个仓库运行起来只有一个可执行程序，那么这个程序只有一个进程。而单例模式是在同一个进程内实现，因此在这个情况下，使用单例模式算是多线程。

不过需要注意的是，如果你将这个可执行程序在多台机器上运行，每台机器只有一个进程，那么这时使用单例模式就是多进程的了。

或者这个程序开了2次（类似开了2个QQ），这也算多进程了。









{% tabs align:left style:boxed %}


<!-- tab id:vsion-bridge-python-old 'icon:fas fa-file-code' title:chat_gpt/python -->

```python
import threading
import time

def singleton(cls):
    instances = {}
    lock = threading.Lock()

    def get_instance(*args, **kwargs):
        with lock:
            if cls not in instances:
                instances[cls] = cls(*args, **kwargs)
            return instances[cls]
    return get_instance

@singleton
class MyClass:
    def __init__(self):
        self.data = []

    def add_data(self, value):
        self.data.append(value)

    def print_data(self):
        print(self.data)




def worker():
    obj = MyClass()
    obj.add_data(threading.current_thread().name)
    obj.print_data()

if __name__ == "__main__":
    threads = []
    for i in range(5):
        t = threading.Thread(target=worker)
        threads.append(t)
        t.start()

    for t in threads:
        t.join()

```


```shell
(sss) xyz@xyz-Workstation:~/xyz_app/software/xyz-robot-toolbox$ /usr/bin/python3.8 /home/xyz/xyz_app/software/xyz-robot-toolbox/test.py
['Thread-1']
['Thread-1', 'Thread-2']
['Thread-1', 'Thread-2', 'Thread-3']
['Thread-1', 'Thread-2', 'Thread-3', 'Thread-4']
['Thread-1', 'Thread-2', 'Thread-3', 'Thread-4', 'Thread-5']
```
在主程序中，我们创建了5个线程并将它们都分配到worker函数中。在worker函数中，我们创建了一个MyClass的实例，向数据列表中添加了线程的名字，并打印出所有已经添加的数据。这里由于MyClass是单例模式，所以我们可以保证只会创建一个实例。

我们可以看到，所有的线程都共享了同一个MyClass实例，并且数据也被正确地添加到了数据列表中。

<!-- endtab -->



<!-- tab id:vsion-bridge-cpp 'icon:fas fa-file-code' title:vision_bridge/C++ -->

``wm_vision_bridge.hpp``

```cpp
#pragma once

#include <map>
#include <string>
#include <memory>

#include <cyber/cyber.h>
#include <wm_msgs/vision_msgs/VisionSrv.pb.h>

namespace wm_vision_bridge {

class WMVisionBrdige {
 public:
  using VisionSrvRequest = wm_msgs::vision_msgs::Request;
  using VisionSrvResponse = wm_msgs::vision_msgs::Response;
  using VisionClient = std::shared_ptr<apollo::cyber::Client<VisionSrvRequest, VisionSrvResponse>>;
  using Primitive2DVector = google::protobuf::RepeatedPtrField<wm_msgs::vision_msgs::Primitive2D>;
  using Primitive3DVector = google::protobuf::RepeatedPtrField<wm_msgs::vision_msgs::Primitive3D>;

  static WMVisionBrdige &get_instance(const std::string &node_name = "vision_bridge_cpp") {
    static WMVisionBrdige instance(node_name);
    return instance;
  }

  WMVisionBrdige(const WMVisionBrdige &) = delete;
  WMVisionBrdige &operator=(const WMVisionBrdige &) = delete;

  std::shared_future<std::shared_ptr<VisionSrvResponse>> async_run(
      int service_id, const std::string &cmd, const std::string &info = "",
      const Primitive2DVector &primitives_2d = {}, const Primitive3DVector &primitives_3d = {}) {
    if (vision_clients_.find(service_id) == vision_clients_.end()) {
      vision_clients_[service_id] = nh_->CreateClient<VisionSrvRequest, VisionSrvResponse>(
          "vision_" + std::to_string(service_id));
    }
    auto request = std::make_shared<VisionSrvRequest>();
    request->set_mode(cmd);
    request->set_info(info);
    request->mutable_primitives_2d()->CopyFrom(primitives_2d);
    request->mutable_primitives_3d()->CopyFrom(primitives_3d);
    return vision_clients_[service_id]->AsyncSendRequest(request);
  }

  std::shared_ptr<VisionSrvResponse> run(
      int service_id, const std::string &cmd, const std::string &info = "",
      const google::protobuf::RepeatedPtrField<wm_msgs::vision_msgs::Primitive2D> &primitives_2d =
          {},
      const google::protobuf::RepeatedPtrField<wm_msgs::vision_msgs::Primitive3D> &primitives_3d =
          {}) {
    return async_run(service_id, cmd, info, primitives_2d, primitives_3d).get();
  }

 private:
  explicit WMVisionBrdige(const std::string &node_name) {
    nh_ = apollo::cyber::CreateNode(node_name);
  }

  std::shared_ptr<apollo::cyber::Node> nh_;
  std::map<int, VisionClient> vision_clients_;
};

} 
```

``test.cc``

```cpp
#include <nlohmann/json.hpp>

#include "xyz_vision_lib/xyz_vision_bridge.hpp"

using json = nlohmann::json;

int main(int argc, char* argv[]) {
  apollo::cyber::Init(argv[0], true);
  auto& b = xyz_vision_bridge::XYZVisionBrdige::get_instance();

  // capture image
  auto res = b.run(0, "capture_images");
  std::cout << res->error_msg() << std::endl;

  // async run
  auto future_res = b.async_run(0, "capture_images");
  res = future_res.get();
  std::cout << res->error_msg() << std::endl;

  // eye in hand: capture image with specific tf_world_hand([x,y,z,qx,qy,qz,qw])
  std::vector<double> tf_world_hand{
      1.9139124755492403, 0.025414824065364546,   1.1459845120164724,   0.007480194470095221,
      0.9999685137439158, -0.0011243741082982916, 0.0023987484942481984};
  json info;
  info["tf_world_hand"] = tf_world_hand;
  res = b.run(0, "capture_images", info.dump());
  std::cout << res->error_msg() << std::endl;

  // calculate object poses
  res = b.run(0, "calculate_object_poses");
  std::cout << res->error_msg() << std::endl;

  // calculate with local help
  res = b.run(0, "calculate_object_poses", "", res->primitives_2d());
  std::cout << res->error_msg() << std::endl;

  // calculate given object's dimension
  if (res->primitives_3d_size() > 0) {
    xyz_vision_bridge::XYZVisionBrdige::Primitive3DVector primitives_3d;
    *primitives_3d.Add() = res->primitives_3d(0);
    res = b.run(0, "calculate_object_poses", "", {}, primitives_3d);
    std::cout << res->error_msg() << std::endl;
  }

  return 0;
}
```


<!-- endtab -->


<!-- tab active id:vsion-bridge-python 'icon:fas fa-cubes' title:vision_bridge/python-max-新版 -->

``wm_vision_bridge.py``

```python
import json

import cyber.cyber_py3.cyber as cyber
from wm_msgs.vision_msgs import VisionSrv_pb2


def singleton(cls):
    instances = {}

    def get_instance(*args, **kwargs):
        if cls not in instances:
            instances[cls] = cls(*args, **kwargs)
        return instances[cls]
    return get_instance


@singleton
class WMVisionBridge:
    """
    This bridge class provides convenient API functions for other programs.
    """

    def __init__(self, node_name="vision_bridge_py"):
        if not cyber.ok():
            cyber.init()
        self._node = cyber.Node(node_name)
        self._env_client = self._node.create_client("env_server", VisionSrv_pb2.Request, VisionSrv_pb2.Response)
        self._vision_clients = {}

    def async_run(self, service_id, cmd, info="", primitives_2d=[], primitives_3d=[]):
        if not isinstance(service_id, int):
            raise TypeError("service id shoud be int")
        if service_id not in self._vision_clients:
            self._vision_clients[service_id] = self._node.create_client("vision_" + str(service_id), VisionSrv_pb2.Request, VisionSrv_pb2.Response)
        request = VisionSrv_pb2.Request()
        request.mode = cmd
        request.info = info
        request.primitives_2d.extend(primitives_2d)
        request.primitives_3d.extend(primitives_3d)
        return self._vision_clients[service_id].async_send_request(request)

    def run(self, service_id, cmd, info="", primitives_2d=[], primitives_3d=[]):
        return self.async_run(service_id, cmd, info, primitives_2d, primitives_3d).get()

    def load_flow_file(self, flow_file):
        """
        THIS API IS FOR XVL TEST ONLY
        """
        req = VisionSrv_pb2.Request()
        req.info = flow_file
        req.mode = "load_flow"
        rsp = self._env_client.send_request(req)
        return rsp


if __name__ == '__main__':
    # how to use
    b = WMVisionBridge()

    # capture image
    res = b.run(0, "capture_images")
    print(res)

    # async run
    future_res = b.async_run(0, "capture_images")
    res = future_res.get()
    print(res)

    # eye in hand: capture image with specific tf_world_hand([x,y,z,qx,qy,qz,qw])
    res = b.run(0, "capture_images", info=json.dumps({"tf_world_hand": [0.1, 0.2, 0.3, 0, 0, 0, 1]}))

    # calculate object poses
    res = b.run(0, "calculate_object_poses")
    print(res)

    # calculate with local help
    res = b.run(0, "calculate_object_poses", primitives_2d=res.primitives_2d)
    print(res)

    # calculate given object's dimension
    res = b.run(0, "calculate_object_dimension", primitives_3d=[res.primitives_3d[0]])
    print(res)

```


``test.py``

```python
from xyz_vision_lib.xyz_vision_bridge import XYZVisionBridge
    
b = XYZVisionBridge()

# capture image
res = b.run(0, "capture_images")
print(res)

# async run
future_res = b.async_run(0, "capture_images")
res = future_res.get()
print(res)

# eye in hand: capture image with specific tf_world_hand([x,y,z,qx,qy,qz,qw])
res = b.run(0, "capture_images", info=json.dumps({"tf_world_hand": [0.1, 0.2, 0.3, 0, 0, 0, 1]}))

# calculate object poses
res = b.run(0, "calculate_object_poses")
print(res)

# calculate with local help
res = b.run(0, "calculate_object_poses", primitives_2d=res.primitives_2d)
print(res)

# calculate given object's dimension
res = b.run(0, "calculate_object_dimension", primitives_3d=[res.primitives_3d[0]])
print(res)

```

<!-- endtab -->



<!-- tab id:vsion-bridge-python-old 'icon:fas fa-file-code' title:vision_bridge/python-max-老版 -->

``wm_vision_bridge.py``
```python

import inspect
import sys
import time

try:
    from queue import Queue, Empty
except ImportError:
    from Queue import Queue, Empty

import cyber.cyber_py3.cyber as cyber

from wm_msgs.vision_msgs import VisionSrv_pb2

from .vision_bridge_base import *
# from .image_converter import *

def capture_image_wrapper(func):
    def wrapper(self, *args, **kwargs):
        if kwargs.pop("capture_image", None):
            self.capture_images(args[0])
        return func(self, *args, **kwargs)

    return wrapper


class WMVisionBridge(VisionBridgeBase):
    """
    This bridge class provides convenient API functions for other programs, such as task planner and rafcon.
    """

    def __init__(self, **kw):
        try:
            super(WMVisionBridge, self) .__init__()
        except TypeError:
            super().__init__()
        self._proxies = {}
        self._mutexes = {}
        self.inited = False

        print("init vision bridge")
        try :
            self ._init()
        except Exception as e :
            print(e)
            print("connect to vision service failed, you can use "
                  "`connect` method to establish connection later")

    def connect(self, timeout = 0):
        t_beg = time.time()
        print("waiting for vision service...")
        while not self.inited:
            try:
                self._init()
            except Exception:
                pass
            if 0 < timeout < time.time() - t_beg:
                break
            time.sleep(1)
        if self.inited:
            print("vision service connection status: success")
        else:
            print("vision service connection status: fail")

    def _init(self):
        self.inited = True

    def run(self, tote_id, cmd, info = "", primitives_2d = [], primitives_3d = [], background = False):
        # tote_id == ws_id
        if not isinstance(tote_id, str):
            tote_id = str(tote_id)
        if tote_id not in self._proxies:
            self._proxies[tote_id] = self._node.create_client("vision_" + tote_id, VisionSrv_pb2.Request, VisionSrv_pb2.Response)
            self._mutexes[tote_id] = threading.Lock()
        with self._mutexes[tote_id]:
            request = VisionSrv_pb2.Request()
            request.mode = cmd
            request.info = info
            request.primitives_2d.extend(primitives_2d)
            request.primitives_3d.extend(primitives_3d)
            try:
                if background:
                       response = self._proxies[tote_id].async_send_request(request)
                else:
                       response = self._proxies[tote_id].send_request(request)
            except:
                response = VisionSrv_pb2.Response()
                response.error = -1
                response.error_msg = "vision service not start"
        return response

    ########################################  API  ######################################
    def load_flow_file(self, flow_file):
        try:
            res = super(WMVisionBridge, self).load_flow_file(flow_file)
        except TypeError:
            res = super().load_flow_file(flow_file)
        if res.error == 0:
            self._init()
        return res

    def capture_images(self, tote_id, background=False):
        # tote_id相当于ws_id
        return self.run(tote_id, inspect.currentframe().f_code.co_name, background=background)

    @capture_image_wrapper
    def calculate_object_poses(self, tote_id, info="", primitives_2d = [], primitives_3d = [], background=False):
        return self.run(tote_id, inspect.currentframe().f_code.co_name, info,
                        primitives_2d, primitives_3d, background=background)

    @capture_image_wrapper
    def collect_partition_data(self, tote_id, background=False):
        return self.run(tote_id, inspect.currentframe().f_code.co_name, background=background)

    def clear_collection(self, tote_id, background=False):
        return self.run(tote_id, inspect.currentframe().f_code.co_name, background=background)

    def calculate_partition(self, tote_id, partition_info=None, background=False):
        if partition_info is not None:
            info_string = json.dumps(partition_info)
        else:
            info_string = ''
        return self.run(tote_id, inspect.currentframe().f_code.co_name,
                        info=info_string, background=background)

    @capture_image_wrapper
    def calculate_barcode_poses(self, tote_id, info="", background=False):
        return self.run(tote_id, inspect.currentframe().f_code.co_name, info=info, background=background)

    @capture_image_wrapper
    def calculate_tote_pose(self, tote_id, background=False):
        return self.run(tote_id, inspect.currentframe().f_code.co_name, background=background)

    @capture_image_wrapper
    def calculate_object_dimension(self, tote_id, obj, background=False):
        return self.run(tote_id, inspect.currentframe().f_code.co_name, primitives_3d = obj, background=background)

    @capture_image_wrapper
    def get_safe_height(self, tote_id, object_point=None, background=False):
        if object_point:
            info_string = str(object_point[0]) + "," + str(object_point[1])
        else:
            info_string = ""
        return self.run(tote_id, inspect.currentframe().f_code.co_name, info=info_string, background=background)

    @capture_image_wrapper
    def calculate_layer_num(self, tote_id, layer_height=.0, background=False):
        info_string = str(layer_height)
        return self.run(tote_id, inspect.currentframe().f_code.co_name, info=info_string, background=background)

    @capture_image_wrapper
    def calculate_place_poses(self, tote_id, dimension=(.1, .1, .1), background=False):
        info_string = json.dumps({
            "dimension": {"length": dimension[0], "width": dimension[1], "height": dimension[2]}
        })
        return self.run(tote_id, inspect.currentframe().f_code.co_name, info=info_string, background=background)

    @capture_image_wrapper
    def double_picking_check(self, tote_id, dimension=(.1, .1, .1), background=False):
        def post_fun(resp):
            res = {
                "error": resp.error,
                "error_message": resp.error_msg,
                "results": False,
                "dimension": [],
                "timestamp": resp.timestamp,
                "info": resp.info
            }
            if res["error"] == 0:
                for obj_idx, obj in enumerate(resp.objects):
                    # confidence of double
                    # double picking if results is True
                    # confidence == 1.0 if it is highly confident double picking
                    # confidence <= 0.0 if it is a single picking
                    # confidence == -1.0 if there is no object detected
                    res["results"] = obj.score > 0
                    res["dimension"] = point_msg_to_list(obj.dimension)
                    res["info"] = str(obj.score)
            return res

        info_string = json.dumps({
            "dimension": {"length": dimension[0], "width": dimension[1], "height": dimension[2]}
        })
        return self.run(tote_id, inspect.currentframe().f_code.co_name, info=info_string, background=background)

    @capture_image_wrapper
    def calculate_object_poses_by_depth(self, tote_id, background=False):
        return self.run(tote_id, inspect.currentframe().f_code.co_name, background=background)

    @capture_image_wrapper
    def check_primitive_cloud(self, tote_id, background=False):
        return self.run(tote_id, inspect.currentframe().f_code.co_name, background=background)

    @capture_image_wrapper
    def check_collision_free(self, tote_id, point_info=[1, 1, 1, 1, 0, 0, 0.1, 0.1, 0.1],
                             background=False):
        info_string = json.dumps(
            {"suction_point": [point_info[0], point_info[1], point_info[2]],
             "suction_normal": [point_info[3], point_info[4], point_info[5]],
             "tool_radius": point_info[6],
             "tool_length": point_info[7],
             "collision_th_area": point_info[8]})

        return self.run(tote_id, inspect.currentframe().f_code.co_name, info=info_string, background=background)

if __name__ == '__main__':
    b = WMVisionBridge()
    ### how to use
    # 1. capture image
    b.run("0", "capture_images")
    # 2. calculate object poses
    res = b.run("0", "calculate_object_poses")
    # 3. use whole primitive info
    b.run("0", "get_safe_height", primitives_3d = res.primitives_3d)
    # 4. use single primitive info
    b.run("0", "get_safe_height", primitives_3d = [res.primitives_3d[0]])
    # 5. check primitive cloud completeness
    b.run("0", "check_primitive_cloud")
```

<!-- endtab -->





{% endtabs %}




### 异步

异步：立马返回，结果在后面拿，而不是死等

{% tabs align:left style:boxed %}


<!-- tab id:max-bridge 'icon:fas fa-file-code' title:Queue做异步 -->

如果api本身不提供异步功能，则这里使用了多线程配合queue的方法来实现异步的功能

老版的``xyz_max_bridge.py``


```python
import inspect
import sys
import time
import threading

try:
    from queue import Queue, Empty
except ImportError:
    from Queue import Queue, Empty

import yaml
import cyber.cyber_py3.cyber as cyber
from wm_msgs.vision_msgs import VisionSrv_pb2
from .msg_utils import *

class Getter:
    def __init__(self):
        self.ret = None

    def get(self, timeout=60):
        ## 因为我们是用queue来实现数据存储的，queue获得内容的函数是get()
        ## 所以这里也封装了个get()
        t = time.time()
        while self.ret is None:
            if time.time() - t >= timeout:
                print("reach out timeout, return none")
                raise Exception("reach out timeout, return none")
        return self.ret

    def set(self, r):
        self.ret = r

def capture_image_wrapper(func):
    def wrapper(self, *args, **kwargs):
        if kwargs.pop("capture_image", None):
            self.capture_images(args[0])
        return func(self, *args, **kwargs)

    return wrapper


class WMMaxBridge():
    """
    This bridge class provides convenient API functions for other programs, such as task planner and rafcon.
    """

    def __init__(self, **kw):
        if not cyber.ok():
            cyber.init()
        self._node = cyber.Node("Max Client")
        self._client = self._node.create_client("project_server", VisionSrv_pb2.Request, VisionSrv_pb2.Response)
        self._base_mutex = threading.Lock()
        self._proxies = {}
        self._mutexes = {}
        self._task_queue = {}
        self._inited = True
        print("init max bridge success")

    def connect(self, timeout = 0):
        t_beg = time.time()
        print("waiting for vision service...")
        while not self._inited:
            try:
                self._inited = True
            except Exception:
                pass
            if 0 < timeout < time.time() - t_beg:
                break
            time.sleep(1)
        if self._inited:
            print("vision service connection status: success")
        else:
            print("vision service connection status: fail")

    def run(self, tote_id, cmd, info = "", cb = None, getter = None, primitives_2d = [], primitives_3d = []):
        with self._mutexes[tote_id]:
            request = VisionSrv_pb2.Request()
            request.mode = cmd
            request.info = info
            request.primitives_2d.extend(primitives_2d)
            request.primitives_3d.extend(primitives_3d)
            try:
                ## 这么写，就是同步访问
                response = self._proxies[tote_id].send_request(request)

                ## call back的写法
                if cb:
                    response = cb(response)
            except:
                response =  {"error": -1,
                         "error_message": "vision service not start",
                         "results": False,
                         "dimension": [],
                         "timestamp": 0,
                         "info": "" }
        # 上面执行完毕以后，如果是异步的，则会去将等了很久的结果设置进去，之后对getter.get()就可以拿到数据了
        if getter:
            getter.set(response)
        return response

    def _call(self, background, tote_id, cmd_name, info_str, post_fun):
        tote_id=str(tote_id)
        if tote_id not in self._proxies:
            self._proxies[tote_id] = self._node.create_client("vision_" + tote_id, VisionSrv_pb2.Request, VisionSrv_pb2.Response)
            self._mutexes[tote_id] = threading.Lock()
            self._task_queue.update({tote_id: Queue()})
            if sys.version_info.major > 2:
                threading.Thread(target=self._start(tote_id), daemon=True).start()
            else:
                threading.Thread(target=self._start(tote_id)).start()
        if background:
            # 立马返回一个 getter（有点像自己构建的cyber future）
            # 这个getter其实是个queue，在下一次真的想拿结果的时候，再去get()
            return self._add_task([tote_id, cmd_name, info_str, post_fun])
        else:
            # 马上同步执行并等对方的结果
            return self.run(tote_id, cmd_name, info_str, post_fun)

    def _start(self, t_id):
        def _start_fun():
            while True:
                item = self._task_queue[t_id].get()
                self.run(*item)
                self._task_queue[t_id].task_done()
        return _start_fun

    def _add_task(self, task):
        ## 返回getter
        ## 参数task是个list: [tote_id, cmd_name, info_str, post_fun]
        getter = Getter()
        self._task_queue[str(task[0])].put(tuple(task + [getter]))  # task[0] = tote_id


        """
        self._task_queue : {
        tote_id: [tote_id, cmd_name, info_str, post_fun, getter]
        }
        """


        return getter

    def __delete__(self, instance):
        [q.join() for q in self._task_queue.values()]

    def __del__(self):
        [q.join() for q in self._task_queue.values()]

    ########################################  API  ######################################
    def load_project_file(self, project_file):
        """load env file

        Args:
            env_file (str): env file path

        Returns:
            dict: {"results": bool, "error": int, "error_message": str}
        """
        with self._base_mutex:
            req = VisionSrv_pb2.Request()
            req.mode = "load_project_file"
            req.info = str(project_file)
            rsp = self._client.send_request(req)
        res = {"error": rsp.error,
               "error_message": rsp.error_msg}
        if res["error"] == 0:
            res["results"] = True
        return res

    def load_flow_file(self, tote_id, flow_name):
        with self._base_mutex:
            req = VisionSrv_pb2.Request()
            req.mode = "load_flow_file"
            req.info = str(tote_id) + "," + str(flow_name)
            rsp = self._client.send_request(req)
        res = {"error": rsp.error,
               "error_message": rsp.error_msg}
        if res["error"] == 0:
            res["results"] = True
        return res

    def set_tote_scan_pose(self, tote_id, pose):
        """set scan pose for specific tote id
        Args:
            tote_id (str): tote_id
            pose (list): [x, y, z, qx, qy, qz, qw] (unit: meter)

        Returns:
            dict: {"results": bool, "error": int, "error_message": str, "timestamp": int64}
        """
        info = str(tote_id) + ',' + ','.join(list(map(str, pose)))
        with self._base_mutex:
            req = VisionSrv_pb2.Request()
            req.mode = "set_tote_scan_pose"
            req.info = info
            rsp = self._client.send_request(req)
        res = {"error": rsp.error,
               "error_message": rsp.error_msg}
        if res["error"] == 0:
            res["results"] = True
        return res

    def capture_images(self, tote_id, background=False):
        return self._call(background, tote_id, inspect.currentframe().f_code.co_name, "",
                          normal_msg_to_dict)

    @capture_image_wrapper
    def calculate_object_poses(self, tote_id, annotation_json_string="", background=False):
        return self._call(background, tote_id, inspect.currentframe().f_code.co_name, annotation_json_string,
                          lambda resp: primitive_msg_to_dict(resp))

    @capture_image_wrapper
    def collect_partition_data(self, tote_id, background=False):
        return self._call(background, tote_id, inspect.currentframe().f_code.co_name, "",
                          normal_msg_to_dict)

    def clear_collection(self, tote_id, background=False):
        return self._call(background, tote_id, inspect.currentframe().f_code.co_name, "reset",
                          normal_msg_to_dict)

    def calculate_partition(self, tote_id, partition_info=None, background=False):
        if partition_info is not None:
            info_string = json.dumps(partition_info)
        else:
            info_string = ''
        return self._call(background, tote_id, inspect.currentframe().f_code.co_name, info_string,
                          lambda resp: dict(results=partition_info_to_list(resp.info), **normal_msg_to_dict(resp)))

    @capture_image_wrapper
    def calculate_barcode_poses(self, tote_id, annotation_json_string="", background=False):
        return self._call(background, tote_id, inspect.currentframe().f_code.co_name, annotation_json_string,
                          lambda resp: primitive_msg_to_dict(resp, self._subscribe_cloud))

    @capture_image_wrapper
    def calculate_tote_pose(self, tote_id, background=False):
        return self._call(background, tote_id, inspect.currentframe().f_code.co_name, "",
                          lambda resp: dict(results=[{
                              "pose": pose_msg_to_list(obj.pose),
                              "dimension": point_msg_to_list(obj.dimension),
                          } for obj_idx, obj in enumerate(resp.objects)] if resp.error == 0 else [], **normal_msg_to_dict(resp)))

    @capture_image_wrapper
    def calculate_object_dimension(self, tote_id, obj, background=False):
        info_string = json.dumps(obj)
        return self._call(background, tote_id, inspect.currentframe().f_code.co_name, info_string,
                          lambda resp: dict(results={
                              "id": -1,
                              "pose": pose_msg_to_list(resp.objects[0].pose),
                              "dimension": point_msg_to_list(resp.objects[0].dimension),
                              "name": resp.objects[0].name,
                              "score": resp.objects[0].score,
                              "grasp_poses": [pose_msg_to_list(x) for x in resp.objects[0].grasp_poses.poses]
                          }, **normal_msg_to_dict(resp)))

    @capture_image_wrapper
    def get_safe_height(self, tote_id, object_point=None, background=False):
        if object_point:
            info_string = str(object_point[0]) + "," + str(object_point[1])
        else:
            info_string = ""
        return self._call(background, tote_id, inspect.currentframe().f_code.co_name, info_string,
                          lambda resp: dict(results={"height": float(resp.info)} if resp.error == 0 else None,
                                            **normal_msg_to_dict(resp)))

    @capture_image_wrapper
    def calculate_layer_num(self, tote_id, layer_height=.0, background=False):
        info_string = str(layer_height)
        return self._call(background, tote_id, inspect.currentframe().f_code.co_name, info_string,
                          lambda resp: dict(results=int(resp.info) if resp.error == 0 else -1,
                                            **normal_msg_to_dict(resp)))

    @capture_image_wrapper
    def calculate_place_poses(self, tote_id, dimension=(.1, .1, .1), background=False):
        info_string = json.dumps({
            "dimension": {"length": dimension[0], "width": dimension[1], "height": dimension[2]}
        })
        return self._call(background, tote_id, inspect.currentframe().f_code.co_name, info_string,
                          primitive_msg_to_dict)

    @capture_image_wrapper
    def double_picking_check(self, tote_id, dimension=(.1, .1, .1), background=False):
        def post_fun(resp):
            res = {
                "error": resp.error,
                "error_message": resp.error_msg,
                "results": False,
                "dimension": [],
                "timestamp": resp.timestamp,
                "info": resp.info
            }
            if res["error"] == 0:
                for obj_idx, obj in enumerate(resp.objects):
                    # confidence of double
                    # double picking if results is True
                    # confidence == 1.0 if it is highly confident double picking
                    # confidence <= 0.0 if it is a single picking
                    # confidence == -1.0 if there is no object detected
                    res["results"] = obj.score > 0
                    res["dimension"] = point_msg_to_list(obj.dimension)
                    res["info"] = str(obj.score)
            return res

        info_string = json.dumps({
            "dimension": {"length": dimension[0], "width": dimension[1], "height": dimension[2]}
        })
        return self._call(background, tote_id, inspect.currentframe().f_code.co_name, info_string,
                          post_fun)

    @capture_image_wrapper
    def calculate_object_poses_by_depth(self, tote_id, background=False):
        return self._call(background, tote_id, inspect.currentframe().f_code.co_name, "",
                          primitive_msg_to_dict)

    def collision_check(self, tote_id, tf_robot_hand_vector, primitive, background=False):
        primitive["grasp_poses"] = [tf_robot_hand for tf_robot_hand in tf_robot_hand_vector]
        return self._call(background, tote_id, inspect.currentframe().f_code.co_name, "",
                          lambda resp: dict(results=[int(x) for x in resp.info.split(",")] if resp.error == 0 else [],
                                            **normal_msg_to_dict(resp)))

    @capture_image_wrapper
    def check_collision_free(self, tote_id, point_info=[1, 1, 1, 1, 0, 0, 0.1, 0.1, 0.1],
                             background=False):
        info_string = json.dumps(
            {"suction_point": [point_info[0], point_info[1], point_info[2]],
             "suction_normal": [point_info[3], point_info[4], point_info[5]],
             "tool_radius": point_info[6],
             "tool_length": point_info[7],
             "collision_th_area": point_info[8]})

        return self._call(background, tote_id, inspect.currentframe().f_code.co_name, info_string,
                          lambda resp: dict(results={"collision_free": int(resp.info)},
                                            **normal_msg_to_dict(resp)))

if __name__ == '__main__':
    b = WMMaxBridge()
    ### how to use
    # 1. load project
    b.load_project_file("xxx")
    # 2. load flow
    b.load_flow_file("xxx.flow")
    # 1. capture image
    b.capture_images(0)
    # 2. calculate object poses
    b.calculate_object_poses(0)

```

<!-- endtab -->


<!-- tab id:vision-bridge 'icon:fas fa-file-code' title:ros/cyber -->

cyber client本身就提供异步功能，所以可以直接拿异步的返回值

``xyz_vision_bridge.py``

```python
'''
Copyright (c) XYZ Robotics Inc. - All Rights Reserved
Unauthorized copying of this file, via any medium is strictly prohibited
Proprietary and confidential
Author: kennycsh <shuohan.chen@xyzrobotics.ai>, Nov, 2021
'''

import json

import cyber.cyber_py3.cyber as cyber
from xyz_msgs.vision_msgs import VisionSrv_pb2


def singleton(cls):
    instances = {}

    def get_instance(*args, **kwargs):
        if cls not in instances:
            instances[cls] = cls(*args, **kwargs)
        return instances[cls]
    return get_instance


@singleton
class XYZVisionBridge:
    """
    This bridge class provides convenient API functions for other programs.
    """

    def __init__(self, node_name="vision_bridge_py"):
        if not cyber.ok():
            cyber.init()
        self._node = cyber.Node(node_name)
        self._env_client = self._node.create_client("env_server", VisionSrv_pb2.Request, VisionSrv_pb2.Response)
        self._vision_clients = {}

    def async_run(self, service_id, cmd, info="", primitives_2d=[], primitives_3d=[]):
        if not isinstance(service_id, int):
            raise TypeError("service id shoud be int")
        if service_id not in self._vision_clients:
            self._vision_clients[service_id] = self._node.create_client("vision_" + str(service_id), VisionSrv_pb2.Request, VisionSrv_pb2.Response)
        request = VisionSrv_pb2.Request()
        request.mode = cmd
        request.info = info
        request.primitives_2d.extend(primitives_2d)
        request.primitives_3d.extend(primitives_3d)
        return self._vision_clients[service_id].async_send_request(request)

    def run(self, service_id, cmd, info="", primitives_2d=[], primitives_3d=[]):
        return self.async_run(service_id, cmd, info, primitives_2d, primitives_3d).get()

    def load_flow_file(self, flow_file):
        """
        THIS API IS FOR XVL TEST ONLY
        """
        req = VisionSrv_pb2.Request()
        req.info = flow_file
        req.mode = "load_flow"
        rsp = self._env_client.send_request(req)
        return rsp


if __name__ == '__main__':
    # how to use
    b = XYZVisionBridge()

    # capture image
    res = b.run(0, "capture_images")
    print(res)

    # async run
    future_res = b.async_run(0, "capture_images")
    res = future_res.get()
    print(res)

    # eye in hand: capture image with specific tf_world_hand([x,y,z,qx,qy,qz,qw])
    res = b.run(0, "capture_images", info=json.dumps({"tf_world_hand": [0.1, 0.2, 0.3, 0, 0, 0, 1]}))

    # calculate object poses
    res = b.run(0, "calculate_object_poses")
    print(res)

    # calculate with local help
    res = b.run(0, "calculate_object_poses", primitives_2d=res.primitives_2d)
    print(res)

    # calculate given object's dimension
    res = b.run(0, "calculate_object_dimension", primitives_3d=[res.primitives_3d[0]])
    print(res)


```

<!-- endtab -->

{% endtabs %}



### 状态机

状态机（State Machine）是一种常见的编程模型，用于描述系统的不同状态以及状态之间的转换规则。状态机通常被用于编写复杂的控制逻辑，例如嵌入式系统、自动化控制、游戏等领域。

在C++中，状态机通常使用状态模式（State Pattern）实现，它将不同的状态封装成不同的类，并通过一定的方式实现状态之间的转换。

以下是一个简单的状态机示例，该状态机用于控制一个电梯的运行。电梯可以在停止、上行和下行三种状态之间切换，具体的状态转换规则如下：

当电梯处于停止状态时，可以切换到上行或下行状态；

当电梯处于上行状态时，可以切换到停止或下行状态；

当电梯处于下行状态时，可以切换到停止或上行状态。


{% tabs align:left style:boxed %}

<!-- tab id:boot_order 'icon:fas fa-file-code' title:开机顺序 -->

```cpp
#include <iostream>

enum class State {
    INIT,
    COUNTING,
    PAUSED
};

class Counter {
public:
    Counter(int init_value) : value_(init_value), state_(State::INIT) {}

    void start() {
        std::cout << "Start counting." << std::endl;
        state_ = State::COUNTING;
    }

    void pause() {
        std::cout << "Pause counting." << std::endl;
        state_ = State::PAUSED;
    }

    void reset() {
        std::cout << "Reset counter." << std::endl;
        state_ = State::INIT;
        value_ = 0;
    }

    void increment() {
        if (state_ == State::COUNTING) {
            ++value_;
            std::cout << "Increment counter: " << value_ << std::endl;
        }
    }

    void decrement() {
        if (state_ == State::COUNTING && value_ > 0) {
            --value_;
            std::cout << "Decrement counter: " << value_ << std::endl;
        }
    }

private:
    int value_;
    State state_;
};

int main() {
    Counter counter(0);
    counter.start();
    counter.increment();
    counter.increment();
    counter.pause();
    counter.increment();
    counter.start();
    counter.increment();
    counter.decrement();
    counter.reset();
    return 0;
}

```


<!-- endtab -->



<!-- tab id:elevator_control 'icon:fas fa-file-code' title:电梯控制 -->

```c++
#include <iostream>

// 电梯状态枚举
enum class ElevatorState {
  IDLE,
  MOVING_UP,
  MOVING_DOWN,
  DOOR_OPEN
};

class Elevator {
public:
  Elevator() : currentFloor(0), state(ElevatorState::IDLE) {}

  // 上升方法
  void moveUp() {
    if (state == ElevatorState::IDLE || state == ElevatorState::MOVING_DOWN) {
      state = ElevatorState::MOVING_UP;
      currentFloor++;
      std::cout << "电梯上升至 " << currentFloor << " 层" << std::endl;
      state = ElevatorState::IDLE;
    } else {
      std::cout << "电梯当前无法上升" << std::endl;
    }
  }

  // 下降方法
  void moveDown() {
    if (currentFloor == 0) {
      std::cout << "电梯已在最低层，无法下降" << std::endl;
      return;
    }
    if (state == ElevatorState::IDLE || state == ElevatorState::MOVING_UP) {
      state = ElevatorState::MOVING_DOWN;
      currentFloor--;
      std::cout << "电梯下降至 " << currentFloor << " 层" << std::endl;
      state = ElevatorState::IDLE;
    } else {
      std::cout << "电梯当前无法下降" << std::endl;
    }
  }

  // 开门方法
  void openDoor() {
    if (state == ElevatorState::IDLE) {
      state = ElevatorState::DOOR_OPEN;
      std::cout << "电梯门已打开" << std::endl;
      state = ElevatorState::IDLE;
    } else {
      std::cout << "电梯正在运动，无法开门" << std::endl;
    }
  }

private:
  int currentFloor;
  ElevatorState state;
};

int main() {
  Elevator elevator;

  elevator.openDoor();
  elevator.moveUp();
  elevator.moveUp();
  elevator.moveDown();
  elevator.openDoor();

  return 0;
}


```

<!-- endtab -->

{% endtabs %}


### 多线程同步

参考python代码


