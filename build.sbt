import Dependencies._

lazy val moho = (project in file("."))
  .enablePlugins(AutomateHeaderPlugin)
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    organization := "com.maogogo",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.12.7",
    organizationName := "Maogogo Workshop",
    scalacOptions := Seq("-deprecation",
      "-feature",
      "-language:implicitConversions",
      "-language:postfixOps"),
    startYear := Some(2018),
    licenses += ("Apache-2.0", new URL("https://www.apache.org/licenses/LICENSE-2.0.txt")),
    PB.targets in Compile := Seq(
      scalapb.gen() -> (sourceManaged in Compile).value
    ),
    libraryDependencies ++= commonDependency ++ guiceDependency ++
      akkaDependency ++ driverDependency ++ scalapbDependency ++
      httpDependency ++ json4sDependency
  )