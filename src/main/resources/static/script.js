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
    // console.log("Review ID: " + reviewId);
    getItems();

    function getItems() {
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if(this.readyState == 4 && this.status == 200) {
                const tagItems = JSON.parse(xhr.response);
                // console.log(tagItems);
                renderTags(tagItems);
            }
        };
        xhr.open('GET', './review/' + reviewId + '/tags', true);
        xhr.send();
    }


    // Add New Tag
    
    function addTag(event) { 
        event.preventDefault();
        // console.log(event);
        
        const tagName = event.target.querySelector('[name=tagName]').value;
        const reviewId = event.target.querySelector('[name=reviewId]').value;
        // console.log({
        //     reviewId: +reviewId,
        //     tagName,
        // });
        const addNewTag = JSON.stringify({
            reviewId: +reviewId,
            tagName,
        });
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if(this.readyState == 4 && this.status == 200) {
                // When Finished, update and re-render
                tagItems = JSON.parse(xhr.response);
                // console.log(tagItems);
                renderTags(tagItems);

            }
        };
        xhr.open('PUT', './review/add-new-tag');
        xhr.setRequestHeader(
            'Content-Type',
            'application/json;charset=UTF-8'
        );
        xhr.send(addNewTag);
    }

    // Remove Tag
    function removeTag(tagName) {
        event.preventDefault();
        
        console.log(event);
        
        // const tagName = event.target.querySelector('[name=tagName]').value;
        // const reviewId = event.target.querySelector('[name=reviewId]').value;
        console.log({
            reviewId: +reviewId,
            tagName,
        });
        const removeTag = JSON.stringify({
            reviewId: +reviewId,
            tagName,
        });
        
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                 // When Finished, update and re-render
                tagItems = JSON.parse(xhr.response);
                renderTags(tagItems);
            }
        }
        xhr.open('DELETE', '/review/removetag')
        xhr.setRequestHeader(
            'Content-Type',
            'application/json;charset=UTF-8'
        );
        xhr.send(removeTag)
    }

    // Render Tags onto review
    function renderTags(tagItems) {
        // Get container and clear it
        const tagsListDiv = document.querySelector('#tagsList');
        tagsListDiv.innerHTML = '';

        // For any tags associated with the review, render that tag
        console.log({tagItems});
        tagItems.forEach(tag => {
            // Determine URL for Tags Page
            const tagHref = '/tag?id=' + tag.id;
            
            // Create tag link elements
            const tagName = document.createTextNode(tag.name);
            const tagDiv = document.createElement('div')
            const tagLink = document.createElement('a');
            tagLink.setAttribute('href', tagHref);
            tagLink.appendChild(tagName);
            tagDiv.appendChild(tagLink);

            // tag remove button
            const tagRemoveButton = document.createElement('button');
            tagRemoveButton.innerHTML = '&times;';
            tagRemoveButton.title = 'Remove Tag';
            tagRemoveButton.id = tag.name;
            tagRemoveButton.addEventListener('click', function() {
                removeTag(tag.name);
            });
            tagDiv.appendChild(tagRemoveButton);

            // Append to the end
            tagsListDiv.appendChild(tagDiv);
        })
    }



