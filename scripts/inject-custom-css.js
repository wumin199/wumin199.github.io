'use strict';

hexo.extend.injector.register('head_end', () => {
  const css = hexo.extend.helper.get('css').bind(hexo);
  return css('/css/custom.css');
});
