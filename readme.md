# Reviews Site, Full Stack
## My Notes

  * Review Kit Car Companies. Picked three manufactures to look at
   
 

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
    * [ ] there is a page that lists review categories, each of which links to the (details) page for a specific category.
    * [ ] there is a page that lists the reviews for a chosen category, each of which links to the (details) page for a specific review.
    * [ ] each review detail page has a link to the page for its category.

### Stretch Tasks

#### Tags
  * [ ] Create a `Tag` entity.
  * [ ] Update `Review` so that it can have tags associated with it. A review can have many tags, a tag can be assigned to many reviews.
  * [ ] Display tags on the review details page.
  * [ ] Create a page that displays links to all of the reviews associated with a given tag.

#### Stretchier
  * [ ] Style your tags list template as a tag cloud, making tags which appear more often larger and/or bolder and those that appear less often smaller and/or lower weight.
  * [ ] Allow creation and deletion of tags from a review using `<form>` and `<button>` elements along with the appropriate controller method(s).

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
