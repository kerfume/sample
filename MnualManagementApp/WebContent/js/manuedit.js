$("#department").change(function() {
	$("#group").empty();
	$("#manualSyubetu").empty();

	var department = $(this).val();
	if (department == "") {
		return;
	}

	$.post("groupjsonaction.action", {
		"department" : department
	}, function(groupList) {
		var optionItems = new Array();
		// optionItems.push(new Option("", ""));
		for (key in groupList) {
			optionItems.push(new Option(groupList[key], key));
		}
		$("#group").append(optionItems);
	});
	$.post("manualjsonaction.action", {
		"department" : department,
		"group":"00"
	}, function(manualList) {
		var optionItems = new Array();
		// optionItems.push(new Option("", ""));
		for (key in manualList) {
			optionItems.push(new Option(manualList[key], key));
		}
		$("#manualSyubetu").append(optionItems);
	});
});

$("#group").change(function() {
	$("#manualSyubetu").empty();

	var department = $("#department").val();
	var group = $(this).val();
	if (department == "") {
		return;
	}

	$.post("manualjsonaction.action", {
		"department" : department,
		"group":group
	}, function(manualList) {
		var optionItems = new Array();
		// optionItems.push(new Option("", ""));
		for (key in manualList) {
			optionItems.push(new Option(manualList[key], key));
		}
		$("#manualSyubetu").append(optionItems);
	});
});