seq(Revolver.settings: _*)

seq(lessSettings: _*)

seq(closureSettings:_*)

(resourceManaged in (Compile, LessKeys.less)) <<= (crossTarget in Compile)(_ / "resource_managed" / "main" / "public" / "css")

(resourceManaged in (Compile, ClosureKeys.closure)) <<= (resourceManaged in Compile)(_ / "public" / "javascript")

(LessKeys.filter in (Compile, LessKeys.less)) := "main.less"
