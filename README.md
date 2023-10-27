![Title](./assets/robohash.png)

![Author](./assets/author.png)

[![codecov](https://codecov.io/github/NathanCheshire/JRoboHash/graph/badge.svg?token=FORj9lW2D3)](https://codecov.io/github/NathanCheshire/JRoboHash)

## Intro

This library is a Java/JVM wrapper for the RoboHash image API.

## Using with Gradle

This artifact is not deployed on Maven, thus you will need to use [Jitpack](https://jitpack.io/) to utilize this library. Make sure you have the following line in your `repositories` Gradle config (it is recommended that JitPack be at the
_end_ of the repositories section, [source](https://jitpack.io/docs/)):

`maven { url 'https://jitpack.io' }`

Then add the following to your dependencies: `implementation 'com.github.nathancheshire.jrobohash:COMMIT_HASH'`.

The `COMMIT_HASH` may vary depending on the targeted version (commit SHA) of JRoboHash you would like to use.

## API Support

This client features support for the comprehensive RoboHash API.
Using a [builder pattern](https://refactoring.guru/design-patterns/builder), all supported URL parameters are configurable via this API.

## Getting Started

Using the client is simplistic and intuitive yet also extensible should you require. The default implementation
of `RoboHashRequestBuilder`, that of `RoboHashRequestBuilderImpl`, allows for the following:

```java
RoboHashRequestBuilder requestBuilder = new RoboHashRequestBuilderImpl('MY_AVATAR_KEY')
        .addImageSet(ImageSet.MONSTERS)
        .addImageSet(ImageSet.HUMANS)
        .setSize(new Dimension(500,500))
        .setImageExtension(ImageExtension.JPEG);
```

There are of course additional mutators exposed by `RoboHashRequestBuilder`s to allow customization of all URL
parameters supported by the RoboHash API.

You may pass a `RoboHashRequestBuilder` instance to the `RoboHashRequestHandler` to generate URLs, download images, and save images which reflect the state of a `RoboHashRequestBuilder` instance.

```java
// Building a URL
String builtUrl = RoboHashRequestHandler.buildRequestUrl(requestBuilder);
// Saving the image to a file
RoboHashRequestHandler.saveToFile(requestBuilder, new File("./path/to/my/file/my_image.jpeg"));
```

## Contributing

If you think a feature is missing, or have an idea for improvement, then by all means contribute! Make sure
to follow clean-code styles. I closely follow the principles set forth in books such as Effective Java by Joshua Bloch,
Clean Code, and The Clean Coder by Robert Cecil Martin.

General guidelines are as follows:

- Make sure your implementation closely matches the implementation you see present in the source files (same code style)
- Make sure to add Javadoc to every member, field, class, method, etc.
- Make sure to write unit tests if applicable for your added code and update any tests which might be failing due to a
  modification you made
- Make sure all unit tests are passing before you submit a PR for review
- Make sure that code coverage is maintained at 100% before you submit a PR for review

## Issues

Specific issue formats are not important. Simply ensure an issue exists and is assigned to you before starting on a task
or bug fix. If you create a PR, make sure it references the issue.

## Resources

[RoboHash API Docs](https://robohash.org/)

Raw link: https://robohash.org/

[RoboHash GitHub](https://github.com/e1ven/Robohash)

Raw link: https://github.com/e1ven/Robohash
