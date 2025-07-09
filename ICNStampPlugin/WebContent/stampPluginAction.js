define([
    "dojo/_base/declare",
    "ecm/model/Request",
    "ecm/widget/dialog/MessageDialog"
], function(declare, Request, MessageDialog) {
    return declare("stampPlugin.StampAction", null, {
        isEnabled: function(repository, items, callback) { callback(items.length > 0); },
        execute: function(repository, items, callback) {
            var docIds = [];
            for (var i = 0; i < items.length; i++) { docIds.push(items[i].id); }
            Request.invokePluginService("StampPlugin", "StampService", {
                requestParams: { repositoryId: repository.id, docIds: JSON.stringify(docIds) },
                requestCompleteCallback: function(response) {
                    var dialog = new MessageDialog({ title: "Stamp Completed", message: response.message });
                    dialog.show();
                    if (callback) callback();
                }
            });
        }
    });
});