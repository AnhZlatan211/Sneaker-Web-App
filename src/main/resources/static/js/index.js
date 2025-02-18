// Hide 'navi'
var prePos = 0;
window.onscroll = function() {
  var currentPos = window.pageYOffset;
  if( prePos > currentPos) {
    document.getElementById('navi').style.top = "0px";
  }
  else {
    document.getElementById('navi').style.top = "-75px"
  }
  prePos = currentPos;
}

//Log out and Search
var logout = document.getElementById('logout');
var search = document.getElementById('search');
function displaysearch() {
  search.style.display = "block";
}
function hidesearch() {
  search.style.display = "none";
}
function displaylogout() {
  logout.style.display = "block";
}
function hidelogout() {
  logout.style.display = "none";
}
window.onclick = function(event) {
  if(event.target == search) {
    search.style.display = "none";
  }
  if(event.target == logout) {
    logout.style.display = "none";
  }
}

//Slider
const container = document.querySelector(".container");
const firstCardWidth = container.querySelector(".card").offsetWidth;
const arrowBtns = document.querySelectorAll(".angle i");

let isDragging = false, startX, startScrollLeft;

// Add event listeners for the arrow buttons to scroll the container left and right
arrowBtns.forEach(btn => {
    btn.addEventListener("click", () => {
        container.scrollLeft += btn.id === "left" ? -firstCardWidth : firstCardWidth;
    });
});
const dragStart = (e) => {
    isDragging = true;
    container.classList.add("dragging");
    // Records the initial cursor and scroll position of the container
    startX = e.pageX;
    startScrollLeft = container.scrollLeft;
}
const dragging = (e) => {
    if(!isDragging) return; // if isDragging is false return from here
    // Updates the scroll position of the container based on the cursor movement
    container.scrollLeft = startScrollLeft - (e.pageX - startX);
}
const dragStop = () => {
    isDragging = false;
    container.classList.remove("dragging");
}
container.addEventListener("mousedown", dragStart);
container.addEventListener("mousemove", dragging);
document.addEventListener("mouseup", dragStop);
