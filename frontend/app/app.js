angular
    .module('ssnApp', ['ui.router', 'ssnApp.user'])

    .config(function ($stateProvider) {
        $stateProvider
            .state({
                name: 'home',
                url: '/',
                controller: 'HomeController',
                templateUrl: 'app/home/home.html'
            })
            .state({
                name: 'login',
                url: '/login',
                templateUrl: 'app/login/login.html'
            })
            .state({
                name: 'register',
                url: '/register',
                controller: 'RegisterController',
                templateUrl: 'app/register/register.html'
            });
    });