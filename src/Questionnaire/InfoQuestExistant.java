package Questionnaire;

public class InfoQuestExistant {
	public Patient pat;
	public Tripplon_questionnaire questTriple;
	public Date datequest;

	public InfoQuestExistant(Patient pat, Tripplon_questionnaire questTriple,
			Date datequest) {
		this.pat = pat;
		this.questTriple = questTriple;
		this.datequest = datequest;
	}
}
