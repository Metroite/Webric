package de.metroite.webric.web;

import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.SslOptions;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

public class Server {
    private static final ModContainer modContainer = FabricLoader.getInstance().getModContainer("webric").get();
    private static final Path configDir = FabricLoader.getInstance().getConfigDir().resolve("webric");
    private static final SslOptions ssl = SslOptions.x509( configDir.resolve("keys").resolve("cert.pem").toString(), configDir.resolve("keys").resolve("privkey.pem").toString() );

    public static void start() {
        Jooby app = new Jooby();

        app.setServerOptions(Objects.requireNonNull(new ServerOptions()
                .setSsl(ssl)
                .setPort(8080)
                .setSecurePort(443)
        ));

        app.get("/", ctx -> {
            return new File(modContainer.getPath("web/index.html").toString());
        });

        app.get("/{path}", ctx -> {
            return new File(String.valueOf(modContainer.getPath("web/" + ctx.path("path").value() + ".html" )));
        });

        app.start();
    }
}
