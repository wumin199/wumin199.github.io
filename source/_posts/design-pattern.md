---
title: 设计模式
date: 2023-04-03 14:18:28
tags: 编程笔记
toc: true
password: target2023
comment: false
---


### 单例模式


{% tabs align:left style:boxed %}
<!-- tab id:install-source 'icon:fas fa-file-code' title:C++ -->

{% codeblock "vision bridge" lang:cpp %}
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

{% endcodeblock %}
<!-- endtab -->


<!-- tab active id:install-npm 'icon:fas fa-cubes' title:python -->
{% codeblock "vision bridge" lang:python %}

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

{% endcodeblock %}
<!-- endtab -->


{% endtabs %}




