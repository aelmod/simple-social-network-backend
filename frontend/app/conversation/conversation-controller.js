angular
    .module('ssnApp')
    .controller('ConversationController', ConversationController);

function ConversationController($scope, $http) {
    $http
        .get('/api/conversations')
        .then((res) => {
            $scope.conversations = res.data;
        });
    $scope.getMessages = (conversationId) => {
        $http
            .get('api/conversations/' + conversationId + '/messages')
            .then((res) => {
                $scope.messages = res.data;
            })
    };

    $scope.getAvatar = (userId) => {
        $http
            .post('/api/pictures/last', {userId: userId})
            .then((res) => {
                $scope.avatar = res.data.fullPath;
            }, () => {
                $scope.avatar = 'https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg';
            })
    };
    $scope.sendMessage = (conversationId, messageForm) => {
        $http
            .post('/api/conversations/' + conversationId + '/messages', messageForm)
    }
}