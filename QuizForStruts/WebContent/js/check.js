
function check() {
	var tmp = 0;
	for (i = 0; i < document.QuizScrForm.ans.length; i++) {
		if (document.QuizScrForm.ans[i].checked == true) {
			tmp = 1;
		}
	}
	if (tmp != 1) {
		alert("解答は必ず選択してください！");
		return false;
	} else {
		return true;
	}

}