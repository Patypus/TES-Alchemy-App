package org.pat.howell.tes.alchemyreference.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class representing an ingredient
 */
public class Ingredient implements Parcelable {
	/** Identifying number from database */
	private int ID;
	/** Name of the Ingredient */
	private String Name;
	
	private String EffectOne;
	private String EffectTwo;
	private String EffectThree;
	private String EffectFour;
	
	public static final Parcelable.Creator<Ingredient> CREATOR = 
			new Creator<Ingredient>() {
				@Override
				public Ingredient[] newArray( int size ) {
					return new Ingredient[size];
				}
				
				@Override
				public Ingredient createFromParcel( Parcel source ) {
					return new Ingredient( source );
				}
			};

	public Ingredient( int id, String name, String effect1, String effect2, String effect3, String effect4  ) {
		ID = id;
		Name = name;
		EffectOne = effect1;
		EffectTwo = effect2;
		EffectThree = effect3;
		EffectFour = effect4;
	}
	
	public Ingredient( Parcel in ) {
		ID = in.readInt();
		Name = in.readString();
		EffectOne = in.readString();
		EffectTwo = in.readString();
		EffectThree = in.readString();
		EffectFour = in.readString();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt( this.ID );
		dest.writeString( this.Name );
		dest.writeString( this.EffectOne );
		dest.writeString( this.EffectTwo );
		dest.writeString( this.EffectThree );
		dest.writeString( this.EffectFour );
	}

	@Override
	public boolean equals( Object equalTo ) {
		boolean result = false;
		if ( equalTo instanceof Ingredient ) {
			Ingredient test = (Ingredient) equalTo;
			result =  test.ID == this.ID &&
					  test.Name.equals( this.Name ) &&
					  test.EffectOne.equals( this.EffectOne ) &&
					  test.EffectTwo.equals( this.EffectTwo ) &&
					  test.EffectThree.equals( this.EffectThree ) &&
					  test.EffectFour.equals( this.EffectFour );
		}
		return result;
	}
	
	public int getID() {
		return ID;
	}

	public String getName() {
		return Name;
	}

	public String getEffectOne() {
		return EffectOne;
	}

	public String getEffectTwo() {
		return EffectTwo;
	}

	public String getEffectThree() {
		return EffectThree;
	}

	public String getEffectFour() {
		return EffectFour;
	}	
}