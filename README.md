Wicket JavaScript and jQuery
============================

[![Build Status](https://travis-ci.org/thrau/wicket-js.png?branch=master)](https://travis-ci.org/thrau/wicket-js) &nbsp; [![Coverage Status](https://coveralls.io/repos/thrau/wicket-js/badge.png?branch=master)](https://coveralls.io/r/thrau/wicket-js?branch=master)

wicket-js makes it easy to write and handle custom JavaScript and [jQuery](http://jquery.com/) from within
[Wicket](http://wicket.apache.org/) Components and Behaviors. It helps to separate Wicket Ajax Behavior listeners from
actual client-side UI functionality, while maintaining the coupling towards Wicket Components.

It provides a (rudimentary) [fluent interface](http://en.wikipedia.org/wiki/Fluent_interface) that allows you to build
JavaScript and jQuery expressions in Java.

Example
-------

With wicket-js you can bind arbitrary JavaScript code to Wicket Components in a clean way. It provides basic syntax
elements for typesafe script creation.

```java
component.add(new JsBehavior() {
    @Override
    protected IJavaScript domReadyJs() {
        return new JsCall("alert", "loaded component with markup-id " + id());
    }
});
```


The API also provides a convenient way of writing jQuery expressions and bind client-side jQuery event behavior to
components. This allows for separation of the Wicket AJAX listening mechanisms and the true client-side JavaScript
functionality while keeping the JavaScript in the actuall Component definitions.

```java
component.add(new JQueryEventBehavior("mouseenter mouseleave") {
    @Override
    protected IJavaScript callback() {
        return new JsStatements(
                $(this).toggleClass("entered"),
                $(this).trigger("toggled")
        );
    }
});
```

With jQuery's `trigger` and Wickets `AjaxEventBehavior` you can wire client-side JavaScript behavior with Wicket AJAX
event handling in a clean way while keeping them separate.

```java
component.add(new AjaxEventBehavior("toggled") {
    @Override
    protected void onEvent(AjaxRequestTarget target) {
        System.out.println("selection has changed!");
    }
});
```

Dependencies
------------

* wicket-core 6.11.0
* jackson-databind [2.0,)

## Notice

wicket-js is in alpha phase and subject to rapid development and change
