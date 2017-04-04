angular
    .module('ssnApp', ['ui.router', 'ssnApp.user'])

    .config(function ($stateProvider) {
        $stateProvider
            .state({
                name: 'home',
                url: '/home',
                controller: 'HomeController',
                templateUrl: 'app/home/home.html'
            })
            .state({
                name: 'hello',
                url: '/hello',
                template: '<h3>hello world!</h3>'
            });
    });