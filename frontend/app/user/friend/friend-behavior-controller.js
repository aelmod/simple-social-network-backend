angular
    .module('ssnApp.user')
    .controller('UserFriendBehaviorController', UserFriendBehaviorController);

function UserFriendBehaviorController($scope, $http) {
    $scope.addFriend = (userId) => {
        $http
            .post('/api/users/friends', JSON.stringify({userId: userId}))
    };

    $scope.acceptFriendshipRequest = (userId) => {
        $http
            .put('/api/users/friends', {userId: userId})
    };

    $scope.rejectFriendshipRequest = (userId) => {
        $http({
            url: '/api/users/friends',
            method: 'DELETE',
            data: {
                userId: userId
            },
            headers: {
                "Content-Type": "application/json;charset=utf-8",
            }
        })
    };
}