# Demo

## MCP Server

Claude Code supports **MCP (Model Context Protocol)** servers, which expose tools that Claude can use directly within the conversation.

### The `/mcp` command

The `/mcp` command lists connected servers and their available tools.

```
> /mcp
```

**Output:**

```
● sonarqube  (17 tools)

   change_sonar_issue_status        open-world
   search_my_sonarqube_projects     read-only, open-world
   search_sonar_issues_in_projects  read-only, open-world
   ...
```

### Discuss directly with Sonar

```
What is the Sonar project status? Present it in a way that I can share with my CISO.
What's your opinion regarding the Opened Security Hotspot you found?
```
