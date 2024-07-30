package cc.codedhyan.codeitup.jgit;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;

public class gitFS {
    @Test
    public void gitfs() throws GitAPIException, IOException, InterruptedException {
        String repoUrl = "https://github.com/Bijit-Mondal/algorithmic-arena.git";
        String path = "apps/problems/classroom"; // desired path

        Collection<Ref> refs = Git.lsRemoteRepository().setRemote(repoUrl).call();
        for (Ref ref : refs) {
            String refName = ref.getName();
            if (refName.startsWith("refs/heads/")) { // consider only branches
                String branchName = refName.substring(11); // extract branch name
                System.out.println("Branch: " + branchName);

                // Use GitHub API to list files in the specified path
                String apiUrl = "https://api.github.com/repos/Bijit-Mondal/algorithmic-arena/contents/" + path + "?ref=" + branchName;
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(apiUrl))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String responseBody = response.body();
                System.out.println("Files in " + path + " on branch " + branchName + ":");
                System.out.println(responseBody);
            }
        }
    }

    @Test
    public void getContent() throws IOException, InterruptedException {
        String fileUrl = "https://raw.githubusercontent.com/Bijit-Mondal/algorithmic-arena/main/apps/problems/classroom/Structure.md";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(fileUrl))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String fileContent = response.body();
        System.out.println("File content:");
        System.out.println(fileContent);
    }



}
