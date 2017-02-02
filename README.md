# jenkins-github-release
A Jenkins Shared Library for creating Github Releases

## Requirements
* Jenkins 2.x

## Usage
1. A file named ```CHANGELOG.json``` in your repository that looks like this:
```
{
  "version": "1.0.1",
  "features": [
    "some feature"
  ],
  "bugfixes": [
    "some bugfix"
  ]
}
```

2. Add this Library to Jenkins the following ways:
  * Global Library([Manage Jenkins] -> [Configure System] -> [Global Pipeline Libraries])
  * Job Library([Configure] -> [Pipeline Libraries])
