const title = document.querySelector("#title");
const button = document.querySelector("#btn");

button.addEventListener("click", function () {
  title.textContent = "버튼을 클릭했습니다!";
});
