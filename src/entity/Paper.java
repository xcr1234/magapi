package entity;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Paper  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -698121851699656815L;
	@SerializedName("Id")
	private Long id;
	@SerializedName("Ti")
	private String paperTitle;
	@SerializedName("Y")
	private Integer paperYear;
	@SerializedName("D")
	private String date;
	@SerializedName("CC")
	private Integer citationCount;
	@SerializedName("AA")
	private List<Author> authors;
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> author) {
		this.authors = author;
	}
	@SerializedName("F")
	private List<Field> fields;
	@SerializedName("J")
	private Journal journal;
	@SerializedName("C")
	private Conference conference;
	@SerializedName("RId")
	private Long 	Reference ;
	@SerializedName("W")
	private String words;
	@SerializedName("E")
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
	public Long getReference() {
		return Reference;
	}
	public void setReference(Long reference) {
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
	
	
	
}
