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

    // Get a list of tags for a review with AJAX
    let tagItems = [];
    let reviewId = document.querySelector('#reviewId').textContent;
    console.log("Review ID: " + reviewId);
    getItems();

    function getItems() {
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if(this.readyState == 4 && this.status == 200) {
                const tagItems = JSON.parse(xhr.response);
                console.log(tagItems);
                renderTags(tagItems);
            }
        };
        xhr.open('GET', './review/' + reviewId + '/tags', true);
        xhr.send();
    }

    function renderTags(tagItems) {
        // Get container and clear it
        const tagsListDiv = document.querySelector('#tagsList');
        tagsListDiv.innerHTML = '';

        // For any tags associated with the review, render that tag
        console.log({tagItems});
        tagItems.forEach(tag => {
            // Determine URL for Tags Page
            const tagHref = '/tag?id=' + tag.id;
            
            // Create tag link element
            const tagName = document.createTextNode(tag.name);
            const tagP = document.createElement('p')
            const tagLink = document.createElement('a');
            tagLink.setAttribute('href', tagHref);
            tagLink.appendChild(tagName);
            tagP.appendChild(tagLink);

            // Append to the end
            tagsListDiv.appendChild(tagP);
        })
    }



