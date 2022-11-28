package com.jsfcourse.calc;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CalcBB {
	private String kwota;
	private String oproc;
	private String raty;
	private Double result;
	private Double years;
	private Double q;

	@Inject
	FacesContext ctx;

	

	public String getKwota() {
		return kwota;
	}

	public void setKwota(String kwota) {
		this.kwota = kwota;
	}

	public String getOproc() {
		return oproc;
	}

	public void setOproc(String oproc) {
		this.oproc = oproc;
	}
	
	public String getRaty() {
		return raty;
	}

	public void setRaty(String raty) {
		this.raty = raty;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public String calc() {
		try {
			double kwota = Double.parseDouble(this.kwota);
			double oproc = Double.parseDouble(this.oproc);
			double raty = Double.parseDouble(this.raty);
			
			q = 1+((oproc/100)/12);
			years = raty * 12;
			
			result = kwota * Math.pow(q, years) * (q-1)/((Math.pow(q, years))-1);
			result = (double) Math.round(result);
			
			
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return "showresult"; 
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B��d podczas przetwarzania parametr�w", null));
			return null; 
		}
				
	}

	public String info() {
		return "info"; 
	}
}
