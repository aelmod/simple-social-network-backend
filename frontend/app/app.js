let app = angular
    .module('ssnApp', ['ui.router', 'ssnApp.user']);
app
    .run(($rootScope) => {
        $rootScope.localStorage = window.localStorage;
    })
    .config(function ($stateProvider) {
        $stateProvider
            .state({
                name: 'home',
                url: '/',
                controller: 'HomeController',
                templateUrl: 'app/home/home.html'
            })
            .state({
                name: 'microblog',
                url: '/microblog/{microblogId:int}',
                // controller: 'MicroblogController',
                templateUrl: 'app/microblog/microblog-view.html'
            })
            .state({
                name: 'login',
                url: '/login',
                templateUrl: 'app/login/login.html'
            })
            .state({
                name: 'upload',
                url: '/upload',
                controller: 'UploadController',
                templateUrl: 'app/upload/upload-view.html'
            })
            .state({
                name: 'register',
                url: '/register',
                controller: 'RegisterController',
                templateUrl: 'app/register/register.html'
            });
    });

app.factory('authInterceptor', ['$q', function ($q) {
    return {
        request: function (config) {
            if (window.localStorage.token !== undefined) {
                config.headers['X-Token'] = window.localStorage.token;
            }
            return config;
        },
        'responseError': function (rejection) {
            if (rejection.status === 401) {
                alert(rejection.data.message);
            }
            if (rejection.status === 500) {
                alert('Error');
            }
            return $q.reject(rejection);
        }
    };
}]);

app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push('authInterceptor');
}]);