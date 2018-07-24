# Reviews, The Next Generation
You can create a new repository or use the same one. If you'd like to use the same one, but would like to keep track of the previous version of your project, you can create a branch or use an annotated tag.

[Here](https://github.com/WeCanCodeIT/java-exercises/blob/master/reviews-site-iteration-3/rubric.md) lies the rubric.

## Review Tags
- [ ] If you didn't include tags before, add them. These should have a many-to-many relationship with reviews.

- [ ] Clicking on a tag should take the user to a page which displays the reviews associated with that tag.

- [ ] Using appropriate HTML elements and AJAX, allow the user to add and remove tags from reviews. Be careful not to create a new tag if a tag with that name already exists.

## Review Comments
- [ ] Add comments to reviews. Comments should be listed on a review's page after the review's content.

- [ ] Create a form on a review's page that adds a comment to a review. Perform an old school form submission rather than using AJAX. After submitting a comment, the user should be returned to the review page, now including the comment that was just submitted.

### Things to ponder
What is the relationship between a review and its comments from the relational database perspective? Does a review or its comment own the relationship?

How can we best semantically represent review comments from an HTML perspective?

## Stretch tasks
 * Create snazzy confirmation prompts for removing tags
 * Use ES6 syntax
 * Oh, you've already thought of a few things. Do those, but keep them simple.

****
# Reviews Site, Full Stack
## My Notes

  * Review Kit Car Companies. Picked three manufactures to look at 

### To Do
  * [x] Fix broken image for tag at the bottom of review detail. _changed to svg_
  * [x] Look at notes from scratch document _scratch document notes added below_
  * [x] Menu should be on the right side _Removed the width for .menuContainer_
  * [x] jscript not working? _Works, feature I thought I had, I actually hadn't done_
  * Ask Aaron:
      * [x] about calling Jscript in Thyme Leaf. I had trouble with that and with the CSS file. _False alarm_
      _it turns out I had it in the right spot, but we fixed the call to the source. I did not need_
      _any other Thyme reference, just put the file in the static folder._
      * The delete method seems to work but:
        * [x] If you happen to have two tags of the same name, the delete fails _we changed the code in Review() so that_
        _deleteTag() did not use an optional and ReviewController().findFirstByName(tagName) instead of .findByName(tagName)._
  * [x] In desktop max view, force size down. Go to default sizes? _yes and set different styles depending on device._
  * [x] Improve the design so that the links to review details are images instead of boring list. Use
  ~Grid or~ FlexBox to arrange the images so that they align properly when resized.
 

## Overview
Return to your reviews site from the [previous exercise](https://wecancodeit.github.io/java-exercises/reviews-site) (or create another).

## Creating a new repository from an existing one
If you are continuing with your reviews site from the previous exercise, create a new GitHub repo based on a clone of your old project repo. Here’s a simple way to go about that:

1. Create another clone with a different name. The `git clone` command will accept an additional argument specifying a folder name, so you can do something like: `git clone http://github.com/myid/reviews-site reviews-site-full-stack`.
2. The `.git` folder is what establishes the folder in which it resides as a git repository. Remove the `.git` folder from this newly cloned repo. You can do this from Windows Explorer or by running `rm -rf .git` from Git Bash. Now it’s no longer a git repository.
3. Treat it now just like any other new project: `git init`, etc.

We’re going to add categories to reviews. Each category will have one or more reviews (a one to many relationship). Each review will be assigned to one category (a many to one relationship).

Also, we’re going to JPA-enable your site so that it writes/reads from an H2 database.

## Tasks
Feel free to use appropriate class names other than `Review` and `Category`, but these instructions will refer to `Review` and `Category`.

  * Add the following dependencies to `build.gradle` (or use [Spring Initializr](https://start.spring.io/) to create a new `build.gradle`)
    * [x] JPA (spring-boot-starter-data-jpa)
    * [x] H2
  * Create a `Category` class that:
    * [x] is a JPA entity.
    * [x] contains an instance variable referencing the `Review`s it contains.
    * [x] configures an appropriate JPA relationship to its reviews.
  * Update the `Review` class such that:
    * [x] it is a JPA entity.
    * [x] configures a JPA relationship to its associated category.
    * [x] allows for a description/content/body longer than 255 characters.
  * Update your view (templates/html/css) such that:
    * [x] there is a page that lists review categories, each of which links to the (details) page for a specific category.
    * [x] there is a page that lists the reviews for a chosen category, each of which links to the (details) page for a specific review.
    * [x] each review detail page has a link to the page for its category.

### Stretch Tasks

#### Tags
  * [x] Create a `Tag` entity.
  * [x] Update `Review` so that it can have tags associated with it. A review can have many tags, a tag can be assigned to many reviews.
  * [x] Display tags on the review details page.
  * [x] Create a page that displays links to all of the reviews associated with a given tag.

#### Stretchier
  * [ ] Style your tags list template as a tag cloud, making tags which appear more often larger and/or bolder and those that appear less often smaller and/or lower weight.
  * [x] Allow creation and deletion of tags from a review using `<form>` and `<button>` elements along with the appropriate controller method(s).

## Grading
Find the rubric [here](https://wecancodeit.github.io/java-exercises/reviews-site-fullstack/rubric.html).

### Tips
#### Start with the known specifics

Start with mapping and displaying your `Review`s. Add `Category`s to them after you’ve gotten that working.

#### Mapping out URLs, Model attributes, view template names, etc
It is good practice to map things out and think them through, using plural and singular names appropriately, or you’ll likely be well confused.

Your names, etc will be different, but hopefully this helps with some of the confusion I’ve seen around naming and what is in the model for a specific view. I’ll append model and view to names to help clarify, though we usually wouldn’t do this in the wild. Create your own table that maps these things out:

| Page Intent | URL (mapped via @RequestMapping) | Description of model attribute | Model Attribute | Retreived Via| View will display | View Template name|
|-------------|----------------------------------|--------------------------------|-----------------|--------------|-------------------|-------------------|
| Categories List | /categories | an `Iterable` of all `Category` objects | "categoriesModel" | repo `findAll` | list of categories | "categoriesView" |
| Category details, including a list of review for teh chosen category | /category?id=42 | the `Category` object associated with `id` | "singleCategoryModel" | rep `findOne` | category detail and list of reviews for that category, each of which links to a review | "singleCategoryView" |
| Review details | /review?id=86 | the `Review` object associated with `id` | "reviewModel" | repo `findOne` | review details | "reviewView" |

#### String fields longer than 255 characters

For instance variables that hold things like descriptions, which may be longer than 255 characters, you will need to indicate that this should be stored in a CLOB (Character Large Object). To do, this use the `@Lob` annotation on your instance variable, like so:

```
@Lob
private String description;
```
See [LOBs, BLOBs, CLOBs and Serialization](https://en.wikibooks.org/wiki/Java_Persistence/Basic_Attributes#LOBs.2C_BLOBs.2C_CLOBs_and_Serialization) in the Java Persistence wikibook.
