(function (angular) {
	var AppController = function ($scope, $resource, Game) {

		$scope.errors = "";
		$scope.view = "game";

		resetParameters();
		Game.query(null, function (response) {
			updateModel(response);
		}, function (err) {
			handleErrors(err);
		});

		$scope.play = function (letter) {
			resetParameters();
			Game.query({
				letter: letter
			}, function (resp) {
				updateModel(resp);
			}, function (err) {
				handleErrors(err);
			});
		}

		$scope.restart = function () {
			resetParameters();
			Game.queryNew(function (resp) {
				updateModel(resp);
			}, function (err) {
				handleErrors(err);
			});
		}

		$scope.showSolution = function () {
			resetParameters();
			Game.getSolution(function (resp) {
				updateModel(resp);
			}, function (err) {
				handleErrors(err);
			});
		}

		function getAllGames() {
			resetParameters();
			Game.getAll(function (resp) {
				$scope.games = resp;
			}, function (err) {
				handleErrors(err);
			});
		}

		$scope.changeView = function (view) {
			$scope.view = view;
			if (view == 'managment') {
				getAllGames();
			}

		}

		function updateModel(response) {
			if (response !== undefined) {
				$scope.currentWord = response.currentWord;
				$scope.ended = response.ended;
				$scope.successful = response.successful;
				$scope.score = response.score;
				$scope.lettersUsed = response.lettersUsed;
				$scope.word = response.word;
				$scope.session = response.sessionId;
			}
		}

		function handleErrors(error) {
			$scope.errors = error.data.message;
		}

		function resetParameters() {
			$scope.letter = "";
			$scope.errors = "";
		}

	};

	AppController.$inject = ['$scope', '$resource', 'Game'];
	angular.module("myApp.controllers").controller("AppController", AppController);
}(angular));