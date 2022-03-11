# Base Exception

[![TravisCI](https://app.travis-ci.com/Fran-Abril/restponse.svg?branch=main)](https://app.travis-ci.com/github/Fran-Abril/restponse)
[![MIT Licensed](https://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat-square)](LICENSE.md)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.fran-abril/restponse.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.fran-abril%22%20AND%20a:%22restponse%22)

Alternative to javax.ws.rs.core.Response to handle all responses easily

## 🔽 How to install

Add te dependency in your pom.xml

```xml
<dependency>
  <groupId>io.github.fran-abril</groupId>
  <artifactId>restponse</artifactId>
  <version>1.0.0</version>
</dependency>
```

## 🚀 How to use

This library uses:
```xml
<dependency>
    <groupId>io.github.fran-abril</groupId>
    <artifactId>base-exception</artifactId>
    <version>1.0.0</version>
</dependency>
```

Go to your Rest Layout.

The new feature needs a registry of code errors. Then create an enum that `implements IError` like this:
```Java
public enum JediError implements IError {
    JEDI_ALREADY_EXIST("Jedi {%s} already exist"),
    JEDI_ALREADY_HAS_PADAWAN("Jedi {%s} already has Padawan {%s}");

    private final String reason;

    JediError(final String reason) {
        this.reason = reason;
    }

    @Override
    public String getCode() {
        return this.getClass().getSimpleName() + "." + this.name();
    }

    @Override
    public String getReason() {
        return reason;
    }
}
```

Go to your exceptions and `extends BaseException` like this:

```Java
public class AlreadyExistsJediException extends BaseException {

    public AlreadyExistsJediException(Jedi jedi) {
        // argument that will be replaced by {%s}
        super(JediError.JEDI_ALREADY_EXIST, jedi.getName());
    }

}

public class JediAlreadyHasPadawanException extends BaseException {

    public JediAlreadyHasPadawanException(Jedi jedi) {
        // argument that will be replaced by {%s}
        super(JediError.JEDI_ALREADY_HAS_PADAWAN, jedi.getName(), jedi.getPadawan().getName());
    }

}
```

Go to your resources and return Restponse or catch you core exceptions and throw ResponseError instead.

```Java
    @POST
    @Path("/")
    public Response createJedi(JediDTO dto) {

        try{
            new Jedi(dto.getName(), dto.getAge(), dto.getLaserSwordColor()).save();
        }catch(AlreadyExistsJediException e){
            throw new RestponseError(e, Status.CONFLICT);
        }

        return Restponse.with(Status.CREATED);
    }

    @GET
    @Path("/{id}")
    public Response getJedi(id) {
        return Restponse.with(Jedi.get(id).toJson());
    }

    @GET
    @Path("/{id}/padawan/{id}")
    public Response setPadawan(masterId, padawanId) {
        try{
            return Restponse.with(Jedi.get(masterId).setPadawan(padawanId).toJson(), Status.CREATED);
        }catch(JediAlreadyHasPadawanException e){
            throw new RestponseError(e, Status.FORBIDDEN);
        }
    }
```

I hope I've helped!
