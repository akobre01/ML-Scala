version := "2.0"

scalaVersion := "2.10.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.0.M1" % "test"

libraryDependencies ++= Seq(
  // other dependencies here
  // pick and choose:
  "org.scalanlp" %% "breeze-math" % "0.1",
  "org.scalanlp" %% "breeze-learn" % "0.1",
  "org.scalanlp" %% "breeze-process" % "0.1",
  "org.scalanlp" %% "breeze-viz" % "0.1",
  "cc.factorie" % "factorie" % "1.0.0-RC1"
)

resolvers ++= Seq(
  // other resolvers here
  // if you want to use snapshot builds (currently 0.2-SNAPSHOT), use this.
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "IESL Release" at "http://dev-iesl.cs.umass.edu/nexus/content/groups/public"
)