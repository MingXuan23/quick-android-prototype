var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
builder.WebHost.UseUrls("http://0.0.0.0:5112");
var app = builder.Build();

// Configure the HTTP request pipeline.

app.UseAuthorization();

app.MapControllers();
app.MapGet("/api/data", () => Results.Ok(new { message = "Test Message", status = "success" }));

// POST API
app.MapPost("/api/data", (dto body) => Results.Ok(new { message = body, value = body.message }));

app.Run();


public class dto
{
    public string message { get; set; }
}