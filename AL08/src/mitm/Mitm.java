package mitm;

import java.math.BigInteger;

import exceptions.DHKeyException;

public class Mitm {
	
	public static Alice alice;
	public static Bob bob;
	public static Eve eve;
	
	public static void main(String[] args) {
		System.out.println("DH MITM Demonstration");
		
		BigInteger p = new BigInteger("21819131983649426195403482320781766999627571533406590885020827352123029444016213256620336927192553000457761939036222072335993327892078998799060773991756872878549743657117755739267412570005254277838034375140074612874259748703113054716983036871237984208894975692919584317208958194001343681265721035272228191940381516967490311853663885038662879081664217500011543564177259511289408698684560208574509988574574931776332832282696965649924861179567537970729499381155140075538067567921666233296643806342970840495497563478900511452395481245863913076346619652063493262101125581002543298674206078759629651075642334806466675621803");
		BigInteger g = new BigInteger("4623692510888167468984204941139933770628576898793032469589753807463460945931989048088222180587277076074886179748441560357608495800516995854247969196552239786362271767623536325293642493039137510238674427350866606287089728810088145068512694869137485854423880941348740219675725498569935177975601526189134804473866177908151538948240349063513698448910090857686196971098953352465593331748656004664748896834722232070373259145274923574855237192183094349453562768707640927022016341224989672869808325727554020592051656744350373842970526216441192660798853316868594928143535159840662220259849510519829556831704289466841786979527");
		alice = new Alice();
		bob = new Bob();
		try {
			eve = new Eve(p,g);
		} catch (DHKeyException e) {
			System.err.println("Eve initialization failed");
			System.exit(0);
		}
		
		//alice.initializeDH();
		//alice.sendMessage("Hi there!");
		//bob.sendMessage("Hi Alice!");
		
		bob.initializeDH();
		alice.sendMessage("Hi there!");
		bob.sendMessage("Hi Alice!");
	}

}