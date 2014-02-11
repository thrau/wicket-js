Wicket JavaScript and JQuery
============================

wicket-js makes it easy to write and handle custom JavaScript and [JQuery](http://jquery.com/) from within
[Wicket](http://wicket.apache.org/) Components and Behaviors. It helps to separate Wicket Ajax Behavior listeners from
actual client-side UI functionality, while maintaining the coupling towards Wicket Components.

It provides a (rudimentary) [fluent interface](http://en.wikipedia.org/wiki/Fluent_interface) that allows you to build
JavaScript and JQuery expressions in Java.

Example
-------

With wicket-js you can bind arbitrary JavaScript code to Wicket Components in a clean way. It provides basic syntax
elements for typesafe script creation.

    component.add(new JsBehavior() {
        @Override
        protected IJavaScript domReadyJs() {
            return new JsCall("alert", "loaded component with markup-id " + id());
        }
    });


The API also provides a convenient way of writing JQuery expressions and bind client-side JQuery event behavior to
components. This allows for separation of the Wicket AJAX listening mechanisms and the true client-side JavaScript
functionality while keeping the JavaScript in the actuall Component definitions.

    component.add(new JQueryEventBehavior("mouseenter mouseleave") {
        @Override
        protected IJavaScript callback() {
            return new JsStatements(
                    $(this).toggleClass("entered"),
                    $(this).trigger("toggled")
            );
        }
    });

With JQuery's `trigger` and Wickets `AjaxEventBehavior` you can wire client-side JavaScript behavior with Wicket AJAX
event handling in a clean way while keeping them separate.

    component.add(new AjaxEventBehavior("toggled") {
        @Override
        protected void onEvent(AjaxRequestTarget target) {
            System.out.println("selection has changed!");
        }
    });

Dependencies
------------

* wicket-core 6.11.0



## Notice

wicket-js is in alpha phase and subject to rapid development and change
