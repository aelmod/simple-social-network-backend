angular
    .module('ssnApp')
    .controller('MicroblogController', MicroblogController);

function MicroblogController($scope, $http) {
    $scope.init = (microblogId) => {
        $http
            .get('/api/microblog/' + microblogId)
            .then((res) => {
                $scope.microblog = res.data;
            });
    };

    $scope.addBlog = (microblogForm) => {
        $http
            .post('/api/microblog/create', microblogForm, {headers: {"X-Token": localStorage.token}})
    }
}