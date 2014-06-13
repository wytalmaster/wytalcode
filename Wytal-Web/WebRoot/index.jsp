
<!doctype html>
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'>
        <title>Wytal - Intelligent Health Records</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
        <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic' rel='stylesheet' type='text/css'>
        <!-- needs images, font... therefore can not be part of ui.css -->
        <link rel="stylesheet" href="http://54.201.175.78:8081/bower_components/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="http://54.201.175.78:8081/bower_components/weather-icons/css/weather-icons.min.css">
        <!-- end needs images -->

            <!-- build:css styles/ui.css -->
            <link rel="stylesheet" href="http://54.201.175.78:8081/bower_components/morris.js/morris.css">
            <!-- endbuild -->
            <link rel="stylesheet" href="http://54.201.175.78:8081/styles/main.css">

    </head>
    <body data-ng-app="app" id="app" data-custom-background data-off-canvas-nav class="nav-min">
        <!--[if lt IE 9]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

        <div data-ng-controller="AppCtrl">
            <div data-ng-hide="isSpecificPage()"
                 data-ng-cloak>
                <section data-ng-include=" 'http://54.201.175.78:8081/views/header.html' "
                     id="header" class="top-header"></section>

                <aside  data-ng-include="getNavPage()"
                     id="nav-container"></aside>
            </div>

            <div class="view-container">
                <section data-ng-view id="content" class="animate-fade-up"></section>
            </div>
        </div>
</body>
</html>
