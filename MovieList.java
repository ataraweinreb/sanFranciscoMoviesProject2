

/**
 * This class inherits from LinkedList and is used to store all the Movie objects.
 * @author atara
 *
 */
public class MovieList extends LinkedList<Movie> {

	/**
	 * Constructs a new MovieList by calling the constructor of it's superclass, LinkedList
	 */
	public MovieList() {
		super();
	}
	
	/**
	 * Adds new movie to the list. Void return.
	 * @param m the movie to be added
	 */
	public void addMovie(Movie m) {
		for (int i = 0; i < size(); i++) {
			if (get(i).equals(m)) { //the movie exists
				get(i).addLocation(m.getSfLocations().get(0)); //so just add the location
				return; //add location and stop
			}
		}
		add(m); //no match exists so add as a new movie
	}
	
	/**
	 * This method should return a list of Movie objects whose titles contain the specified keyword as a substring.
	 * @param title the specified keyword to search for
	 * @return a MovieList called matchingTitles
	 */
	public MovieList getMatchingTitles(String title) {
		MovieList matchingTitles = new MovieList();
		if (title == null || title.trim().length() == 0) {
			return null;
		}
		for (int i = 0; i < size(); i++) {
			String theTitle = get(i).getTitle().trim();
			if (theTitle.toLowerCase().contains(title.trim().toLowerCase())) {
				matchingTitles.add(get(i));
			}
		}
		matchingTitles.sort();
		if (matchingTitles.size() == 0) {
			return null;
		}
		return matchingTitles;
	}
	
	/**
	 * This method should return a list of Movie object whose actors names contain the keyword as a substring.
	 * @param actor the specified keyword to search for
	 * @return MovieList - the type of list to be returned - called matchingActors 
	 */
	public MovieList getMatchingActor(String actor) {
		MovieList matchingActors = new MovieList();
		if (actor == null || actor.trim().length() == 0) {
			return null;
		}
		for (int i = 0; i < size(); i++) {
			if (get(i).getActor1().getName().trim().toLowerCase().contains(actor.trim().toLowerCase())) {
				matchingActors.add(get(i));
			}
			else {
				Actor actor2 = (get(i)).getActor2();
				if (actor2 != null && actor2.getName().trim().toLowerCase().contains(actor.trim().toLowerCase())) {
					matchingActors.add(get(i));
				}
				else {
					Actor actor3 = (get(i)).getActor3();
					if (actor3 != null && actor3.getName().trim().toLowerCase().contains(actor.trim().toLowerCase())) {
						matchingActors.add(get(i));
					}
				}
			}
		}
		matchingActors.sort();
		if (matchingActors.isEmpty()) {
			return null;
		}
		return matchingActors;
	}
}

	