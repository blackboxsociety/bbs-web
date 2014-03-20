Black Box Society
=================

Black Box Society is a project to index academic and research papers
in new and interesting ways. It's also really just a sandbox for some
framework dev i'm doing.

Install
-------

Requires SBT and Scala to be installed locally.

To run the project just run `sbt run` from the project folder, or `sbt ~reStart` for auto-recompile.

To use our LESS feature, put your LESS files in `src/main/less`, link it all up in main.less, and compile as usual.
Access your css source files from your view with `/assets/css/main.less`.

To use the Google Closure Compiler, put all your JavaScript files in `src/main/javascript`, list them all in the
main.jsm file, and compile as usual. Access the compiled files with `/assets/javascript/somejsfile.js`.

You can access the dev environment [here](http://localhost:3000)

Hack
----

PRs and issue submission/participation welcome.