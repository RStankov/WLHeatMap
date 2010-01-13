require "net/http"

paths = %w(
 16/37853/24025.png 
 16/37855/24024.png 
 16/37853/24024.png 
 16/37855/24025.png 
 16/37854/24025.png 
 16/37855/24026.png 
 16/37854/24024.png 
 16/37853/24026.png 
 16/37854/24026.png
 16/37852/24025.png
 16/37852/24024.png
 16/37852/24026.png
 16/37856/24025.png
 16/37856/24026.png
 16/37856/24024.png
 16/37851/24024.png
 16/37851/24026.png
 16/37851/24025.png
)


DIR_NAME  = "../assets/map_cache"
HOST_NAME = "tile.openstreetmap.org"

Net::HTTP.start(HOST_NAME) do |http|
  paths.each do |path|
    file_name = DIR_NAME + "/" + path.gsub("/", "_")
    
    puts "Downloading /#{path} -> #{file_name}"
    
    open(file_name, "wb") do |file|
      file.write(http.get("/#{path}").body)
    end
  end
end