# Development Guide <br/> Kyrio Online Services SDK for Java

This document provides high-level instructions on how to build and test the SDK.

* [Environment Setup](#setup)
* [Installing](#install)
* [Building](#build)
* [Testing](#test)
* [Release](#release)

## <a name="setup"></a> Environment Setup

To be able to develop and test the project you need to install the following components:
- Oracle JDK 8: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
- Maven build tool: https://maven.apache.org/download.cgi
- GPG management tool: https://www.gnupg.org/download/index.html 

To work with GitHub code repository you need to install Git from: https://git-scm.com/downloads

You are free to use IDE of your choice. This guide will focus on use of command-line tools.

## <a name="install"></a> Installing

After your environment is ready you can check out source code from the Github repository:
```bash
git clone git@github.com:KyrioServices/kyrio-services-sdk-java
```

## <a name="build"></a> Building

To compile the source code execute the following command:

```bash
mvn compile
```

To generate source code documentation execute:

```bash
mvn javadoc:javadoc
```

## <a name="test"></a> Testing

Execute tests with the command below:

```bash
mvn test
```

## <a name="release"></a> Release

Detail description of the Maven centtral release procedure 
is described at http://kirang89.github.io/blog/2013/01/20/uploading-your-jar-to-maven-central/

Another useful release guide: https://stackoverflow.com/questions/28846802/how-to-manually-publish-jar-to-maven-central

Before publishing a new release you shall register on Sonatype Repository site.

Then generate gpg key:
```bash
gpg --gen-key
```

In the command output locate KEY_ID:
```bash
....
gpg: key YOUR_KEY_ID marked as ultimately trusted
...
```

Upload the gpg public key to servers:
```bash
gpg2 --keyserver hkp://pool.sks-keyservers.net --send-keys YOUR_KEY_ID
gpg2 --keyserver hkp://pgp.mit.edu --send-keys YOUR_KEY_ID
gpg2 --keyserver hkp://keyserver.ubuntu.com --send-keys YOUR_KEY_ID
```

Save the following settings in ~/.m2/settings.xml file:

```xml
<settings>
  <servers>
      <server>
          <id>ossrh</id>
          <username>kyrio</username>
          <password>...PASSWORD...</password>
      </server>
      <server>
          <id>sonatype-nexus-snapshots</id>
          <username>kyrio</username>
          <password>...PASSWORD...</password>
      </server>
      <server>
          <id>nexus-releases</id>
          <username>kyrio</username>
          <password>...PASSWORD...</password>
      </server>
  </servers>
  <profiles>
      <profile>
          <id>gpg</id>
          <properties>
              <gpg.passphrase>..YOUR_KEY_PASS...</gpg.passphrase>
              <gpg.keyname>...YOUR_KEY_ID...</gpg.keyname>
          </properties>
      </profile>
  </profiles>
</settings>
```

Update release notes in CHANGELOG. Update version number and release details in pom.xml file.

Finally build and release the project. The release process will automatically set and push git tag for the release.

```bash
mvn release:clean
mvn release:prepare
mvn release:perform
```
