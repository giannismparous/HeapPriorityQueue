package erg2;

public class City implements CityInterface,Comparable<City>{

	protected int ID;
	protected String name;
	protected int population;
	protected int CovidCases;

	public City(int ID,String name,int population,int CovidCases) {

        setID(ID);
        setName(name);
        setPopulation(population);
        setCovidCases(CovidCases);

    }
	
	public int getID() {
		return ID;
	}
	
	public String getName() {
		return name;
	}

	public int getPopulation() {
		return population;
	}

	public int getCovidCases() {
		return CovidCases;
	}

	public void setID(int ID) {
		this.ID=ID;
		
	}

	public void setName(String name) {
		this.name=name;
	}

	public void setPopulation(int population) {
		this.population=population;
	}

	public void setCovidCases(int CovidCases) {
		this.CovidCases=CovidCases;
	}
	
	public double calculateDensity() {
		double s = (Math.round(((double)CovidCases*50000)/population * 100));
		return s/100;
		}
	
	public int compareTo(City city) {
		if (calculateDensity()<city.calculateDensity())return -1;
		else if (calculateDensity()>city.calculateDensity())return 1;
		else {
			if (name.compareTo(city.getName())==0) {
				if (ID<city.getID())return -1;
				else return 1;
			}
			else return (-1)*name.compareTo(city.getName());
		}
	}
}
