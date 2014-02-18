seq(Revolver.settings: _*)

seq(lessSettings: _*)

(resourceManaged in (Compile, LessKeys.less)) <<= (crossTarget in Compile)(_ / "../public" / "css")

(LessKeys.filter in (Compile, LessKeys.less)) := "main.less"