using Microsoft.AspNetCore.Mvc;
using System;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddControllersWithViews();

builder.Services.AddDistributedMemoryCache();



builder.Services.AddSession(options =>
{
    options.IdleTimeout = TimeSpan.FromSeconds(1800);

    options.Cookie.HttpOnly = true;

    options.Cookie.IsEssential = true;
});



var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
}
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Landing}/{action=Index}");

app.MapControllerRoute(
    name: "home",
    pattern: "{controller=Home}/{action=Index}");

app.MapControllerRoute(
    name: "item",
    pattern: "{controller=Home}/{action=Game}/{name?}");

app.MapControllerRoute(
    name: "search",
    pattern: "{controller=Search}/{action=Index}/{t}/{q?}");

app.UseSession();

app.Run();

//Développeur ayant travailler sur ce projet. Moi: Jacob Landry et deux coéquipiers: Jean Sébastien Marier et Francis Robert