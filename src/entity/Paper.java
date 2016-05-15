package entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Paper  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -698121851699656815L;
	
	@JsonProperty("Id")
	private Long id;
	
	@JsonProperty("Ti")
	private String paperTitle;
	
	@JsonProperty("Y")
	private Integer paperYear;
	
	@JsonProperty("D")
	private String date;
	
	@JsonProperty("CC")
	private Integer citationCount;
	
	@JsonProperty("AA")
	private List<Author> authors;
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> author) {
		this.authors = author;
	}
	
	@JsonProperty("F")
	private List<Field> fields;
	
	@JsonProperty("J")
	private Journal journal;
	
	@JsonProperty("C")
	private Conference conference;

	@JsonProperty("RId")
	private List<Long> 	Reference ;

	@JsonProperty("W")
	private String words;
	
	@JsonProperty("E")
	private String extend;
	
	public Float getLogprob() {
		return logprob;
	}
	public void setLogprob(Float logprob) {
		this.logprob = logprob;
	}
	private Float logprob;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPaperTitle() {
		return paperTitle;
	}
	public void setPaperTitle(String paperTitle) {
		this.paperTitle = paperTitle;
	}
	public Integer getPaperYear() {
		return paperYear;
	}
	public void setPaperYear(Integer paperYear) {
		this.paperYear = paperYear;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getCitationCount() {
		return citationCount;
	}
	public void setCitationCount(Integer citationCount) {
		this.citationCount = citationCount;
	}
	
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> field) {
		this.fields = field;
	}
	public Journal getJournal() {
		return journal;
	}
	public void setJournal(Journal journal) {
		this.journal = journal;
	}
	public Conference getConference() {
		return conference;
	}
	public void setConference(Conference conference) {
		this.conference = conference;
	}
	public List<Long> getReference() {
		return Reference;
	}
	public void setReference(List<Long> reference) {
		Reference = reference;
	}
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	public String getExtend() {
		return extend;
	}
	public void setExtend(String extend) {
		this.extend = extend;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Paper [id=" + id + ", paperTitle=" + paperTitle
				+ ", paperYear=" + paperYear + ", date=" + date
				+ ", citationCount=" + citationCount + ", author=" + authors
				+ ", field=" + fields + ", journal=" + journal + ", conference="
				+ conference + ", Reference=" + Reference + ", words=" + words
				+ ", extend=" + extend + ", logprob=" + logprob + "]";
	}
	

	public  static  Paper valueOf(Author author){
		Paper paper = new Paper();
		paper.setId(author.getId());
		return paper;
	}
	
	
	
	
}
