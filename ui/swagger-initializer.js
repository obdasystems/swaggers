window.onload = function() {
  //<editor-fold desc="Changeable Configuration Block">
  var api = window.location.search.substring(1).split('=')[1]
  api = api.slice(0, -1)
  // the following lines will be replaced by docker/configurator, when it runs in a docker-container
  window.ui = SwaggerUIBundle({
    url: "../bundled/" + api + ".yaml",
    dom_id: '#swagger-ui',
    deepLinking: true,
    presets: [
      SwaggerUIBundle.presets.apis
    ],
    plugins: [
      SwaggerUIBundle.plugins.DownloadUrl
    ],
  });

  //</editor-fold>
};
