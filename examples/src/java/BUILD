package(default_visibility = ["//visibility:public"])

java_binary(
    name='api-examples-unshaded',
    srcs = glob(["com/twitter/heron/examples/api/**/*.java"]),
    deps = [
        "//heron/api/src/java:api-java-low-level",
        "//heron/common/src/java:basics-java",
        "//heron/simulator/src/java:simulator-java"
    ],
    create_executable = 0,
)

genrule(
    name = 'heron-api-examples',
    srcs = [":api-examples-unshaded_deploy.jar"],
    outs = ["heron-api-examples.jar"],
    cmd  = "cp $< $@",
)

java_binary(
    name='streamlet-examples-unshaded',
    srcs = glob(["com/twitter/heron/examples/streamlet/**/*.java"]),
    deps = [
        "//heron/api/src/java:api-java",
        "//heron/common/src/java:basics-java",
        "//heron/simulator/src/java:simulator-java",
        "//third_party/java:kryo",
        "@apache_pulsar_client//jar",
    ],
    create_executable = 0,
)

genrule(
    name = 'heron-streamlet-examples',
    srcs = [":streamlet-examples-unshaded_deploy.jar"],
    outs = ["heron-streamlet-examples.jar"],
    cmd  = "cp $< $@",
)


java_binary(
    name='eco-examples-unshaded',
    srcs = glob(["com/twitter/heron/examples/eco/**/*.java"]),
    deps = [
        "//heron/api/src/java:api-java-low-level",
        "//heron/api/src/java:api-java",
        "//heron/common/src/java:basics-java",
         "//heron/eco/src/java:eco-core",
        "//storm-compatibility/src/java:storm-compatibility-java",
    ],
    create_executable = 0,
)

genrule(
    name = 'heron-eco-examples',
    srcs = [":eco-examples-unshaded_deploy.jar"],
    outs = ["heron-eco-examples.jar"],
    cmd  = "cp $< $@",
)