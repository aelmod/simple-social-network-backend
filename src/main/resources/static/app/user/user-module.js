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
            })
            .state({
                name: 'user-friends',
                url: '/user/:userId/friends',
                controller: 'UserFriendController',
                templateUrl: 'app/user/friend/friend-list-view.html'
            })
            .state({
                name: 'incoming-request-list',
                url: '/user/:userId/friends/incoming',
                controller: 'FriendIncomingRequestController',
                templateUrl: 'app/user/friend/incoming/incoming-request-list-view.html'
            })
            .state({
                name: 'outgoing-request-list',
                url: '/user/:userId/friends/outgoing',
                controller: 'FriendOutgoingRequestController',
                templateUrl: 'app/user/friend/outgoing/outgoing-request-list-view.html'
            })
            .state({
                name: 'logout',
                url: '/logout',
                controller: 'LogoutController'
            })
            .state({
                name: 'ignore-user',
                url: '/user/ignore/:userId',
                controller: 'UserIgnoreController'
            });
    });