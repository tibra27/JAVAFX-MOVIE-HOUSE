package application;

public class Product {
	private int movie_id;
    private String movie_name;
    private String genre;
    private double price;
    private double imdb;
    private String description;
	private String trailer;
    private String cover_image;
    private String image1;
    private String image2;
    private String download_link;
    
    
    public Product()
    {
    	
    }
    
    
	public Product(int movie_id, String movie_name, String genre, double price, double imdb, String description,
			String trailer, String cover_image, String image1, String image2, String download_link) {
		setMovie_id(movie_id);
		setMovie_name(movie_name);
		setGenre(genre);
		setPrice(price);
		setImdb(imdb);
		setDescription(description);
		setTrailer(trailer);
		setCover_image(cover_image);
		setImage1(image1);
		setImage2(image2);
		setDownload_link(download_link);
		
	}


	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getImdb() {
		return imdb;
	}
	public void setImdb(double imdb) {
		this.imdb = imdb;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	public String getCover_image() {
		return cover_image;
	}
	public void setCover_image(String cover_image) {
		this.cover_image = cover_image;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getDownload_link() {
		return download_link;
	}
	public void setDownload_link(String download_link) {
		this.download_link = download_link;
	}
}
