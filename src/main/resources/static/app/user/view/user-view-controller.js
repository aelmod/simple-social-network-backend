angular
    .module('ssnApp.user')
    .controller('UserViewController', UserViewController);


function UserViewController($scope, $http, $stateParams) {
    $http
        .get('/api/users/' + $stateParams.userId)
        .then((res) => {
            $scope.user = res.data;
        });

    $http
        .post('/api/pictures/last', {userId: $stateParams.userId})
        .then((res) => {
            $scope.avatar = res.data.fullPath;
        }, () => {
            $scope.avatar = 'https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg';
        });

    $scope.addBlog = (microblogForm) => {
        $http
            .post('/api/microblog/create', microblogForm)
            .then((res) => {
                $scope.user.microblogs.unshift(res.data);
                microblogForm.text = '';
            })
    };
}