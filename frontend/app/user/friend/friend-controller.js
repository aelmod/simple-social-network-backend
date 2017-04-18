angular
    .module('ssnApp.user')
    .controller('UserFriendController', UserFriendController);

function UserFriendController($scope, $http) {
    $scope.init = (userId) => {
        $http
            .get('/api/users' + userId + 'friends', {headers: {"X-Token": localStorage.token}})
            .then((res) => {
                $scope.friends = res.data;
            })
    };

    $scope.getOutgoingRequests = () => {
        $http
            .get('/api/users/friends/outgoingRequests', {headers: {"X-Token": localStorage.token}})
            .then((res) => {
                $scope.outgoingRequests = res.data;
            })
    };

    $scope.getIncomingRequests = () => {
        $http
            .get('/api/users/friends/incomingRequests', {headers: {"X-Token": localStorage.token}})
            .then((res) => {
                $scope.outgoingRequests = res.data;
            })
    };

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
            .delete('/api/users/friends', userId, {headers: {"X-Token": localStorage.token}})
    };
}