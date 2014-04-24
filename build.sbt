seq(Revolver.settings: _*)

seq(lessSettings: _*)

seq(closureSettings:_*)

crossPaths := false

(resourceManaged in (Compile, LessKeys.less)) <<= (crossTarget in Compile)(_ / "resource_managed" / "main" / "public" / "css")

(resourceManaged in (Compile, ClosureKeys.closure)) <<= (crossTarget in Compile)(_ / "resource_managed" / "main" / "public" / "javascript")

(LessKeys.filter in (Compile, LessKeys.less)) := "main.less"
