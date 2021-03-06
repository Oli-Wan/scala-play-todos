angular.module("todos").controller('TodoCtrl', ['$scope', '$resource', function($scope, $resource) {

    var Todo = $resource('/tasks/:Id',
        {Id: '@id'},
        { "update": {method:"PUT"} }
    );

    $scope.createTodo = function(valid) {
        if(!valid)
            return;

        var newTodo = new Todo();
        newTodo.label = $scope.label;
        newTodo.$save(function() {
            $scope.messages.push({
                type: "success",
                content: "Task created"
            });
            $scope.todos.push(newTodo)
        }, function(error) {
            $scope.messages.push({
                type: "alert",
                content: "Could not save the task : " + error.status + " " + error.data
            });
        });
    };

    $scope.close = function(index) {
        $scope.messages.splice(index, 1)
    };

    $scope.getTodos = function() {
        $scope.todos = Todo.query();
    };

    $scope.deleteTodo = function(index) {
        $scope.todos[index].$delete(function() {
            $scope.messages.push({
                type: "success",
                content: "Task removed"
            });
            var newTodos = $scope.todos.splice(index, 1);
        }, function(error) {
            $scope.messages.push({
                type: "alert",
                content: "Could not remove the task : " + error.status + " " + error.data
            });
        });
    };

    $scope.completeTodo = function(todo) {
        todo.$update();
    };

    $scope.todos = [];
    $scope.messages = [];

    $scope.getTodos();
}]);