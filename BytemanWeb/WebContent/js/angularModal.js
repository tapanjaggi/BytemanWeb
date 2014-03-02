app = angular.module('ModalDemo', []);
app
		.directive(
				'modalDialog',
				function() {
					return {
						restrict : 'E',
						scope : {
							show : '='
						},
						replace : true, // Replace with the template below
						transclude : true, // we want to insert custom content
											// inside the directive
						link : function(scope, element, attrs) {
							scope.dialogStyle = {};
							if (attrs.width)
								scope.dialogStyle.width = attrs.width;
							if (attrs.height)
								scope.dialogStyle.height = attrs.height;
							scope.hideModal = function() {
								scope.show = false;
							};
						},
						template : "<div class='ng-modal' ng-show='show'><div class='ng-modal-overlay' ng-click='hideModal()'></div><div class='ng-modal-dialog' ng-style='dialogStyle'><div class='ng-modal-close' ng-click='hideModal()'>X</div><div class='ng-modal-dialog-content' ng-transclude></div></div></div>"
					};
				});

app.controller('MyCtrl', [
		'$scope',
		function($scope) {
			Array.prototype.myUcase = function(lineSeparator) {
				$scope.rulesToBeDisplayed = '';
				for (i = 0; i < this.length; i++) {
					$scope.rulesToBeDisplayed = $scope.rulesToBeDisplayed
							+ this[i] + lineSeparator + lineSeparator;
				}
			}
			$scope.modalShown = false;
			$scope.editModalShown = false;
			$scope.newModalShown = false;
			$scope.rules = [];
			$scope.rulesToBeDisplayed = '';
			$scope.toggleModal = function(lineSeparator) {
				$scope.editModalShown = !$scope.editModalShown;
				$scope.rules.myUcase(lineSeparator);
				$scope.modalShown = !$scope.modalShown;
			};
			$scope.toggleEditModal = function() {
				$scope.editModalShown = !$scope.editModalShown;
			};
			$scope.toggleSelection = function(rule) {
				var idx = $scope.rules.indexOf(rule);

				// is currently selected
				if (idx > -1) {
					$scope.rules.splice(idx, 1);
				}

				// is newly selected
				else {
					$scope.rules.push(rule);
				}
			};
			$scope.toggleNewModal = function() {
				$scope.newModalShown = !$scope.newModalShown;
			};
		} ]);