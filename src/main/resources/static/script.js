// nav menu transition
function toggleMenu(menuContainer) {
    
    const growUp = document.querySelector('#mainNav');
    const growUpChild = growUp.querySelector('div');
    
    if (menuContainer.classList.contains('show')) {
        growUp.style.height = '0';
        menuContainer.classList.remove('show');
    }
    else {
        growUp.style.height = growUpChild.offsetHeight + 'px';
        menuContainer.classList.add('show');
    }
}

// Close nav menu after clicking a selection
const mainLinks = document.querySelectorAll('#mainNav a');
const menuContainer = document.getElementById('menuBarsContainer');
for (let link of mainLinks) {
  link.onclick = function() {
    toggleMenu(menuContainer);
  };
}