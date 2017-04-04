angular
    .module('ssnApp.user', ['ui.router'])
    .config(function ($stateProvider) {
        $stateProvider
            .state({
                name: 'user-list',
                url: '/user-list',
                controller: 'UserListController',
                templateUrl: 'app/user/list/user-list.html'
            })
            .state({
                name: 'user-view',
                url: '/user/:userId',
                controller: 'UserViewController',
                templateUrl: 'app/user/view/user-view.html'
            });
    });