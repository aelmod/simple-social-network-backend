angular
    .module('ssnApp.user')
    .controller('UserFriendBehaviorController', UserFriendBehaviorController);

function UserFriendBehaviorController($scope, $http) {
    $scope.addFriend = (userId) => {
        $http
            .post('/api/users/friends', userId, {headers: {"X-Token": localStorage.token}})
    };

    $scope.acceptFriendshipRequest = (userId) => {
        $http
            .put('/api/users/friends', userId, {headers: {"X-Token": localStorage.token}})
    };

    $scope.rejectFriendshipRequest = (userId) => {
        $http
            .defaults
            .headers
            .delete = {"X-Token": localStorage.token};
        $http
            .delete('/api/users/friends', userId)
    };
}